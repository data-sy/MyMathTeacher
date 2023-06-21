
import os
import csv
from utils import DKT
import tensorflow as tf
from load_data import DKTData

tf.compat.v1.logging.set_verbosity(tf.compat.v1.logging.ERROR)

rnn_cells = {
    "LSTM": tf.contrib.rnn.LSTMCell,
    "GRU": tf.contrib.rnn.GRUCell,
    "BasicRNN": tf.contrib.rnn.BasicRNNCell,
    "LayerNormBasicLSTM": tf.contrib.rnn.LayerNormBasicLSTMCell,
}

num_runs = 1
num_epochs = 1
batch_size = 1
keep_prob = 0.8656542586183774

network_config = {}
network_config['batch_size'] = batch_size
network_config['hidden_layer_structure'] = [102, ]
network_config['learning_rate'] = 0.004155923499457689
network_config['keep_prob'] = keep_prob
network_config['rnn_cell'] = rnn_cells['LSTM']
network_config['max_grad_norm'] = 5.0
network_config['lambda_w1'] = 0.03
network_config['lambda_w2'] = 3.0
network_config['lambda_o'] = 0.1

train_path = './data/i-scream/i-scream_train.csv'
valid_path = './data/i-scream/i-scream_valid.csv'
test_path = './data/i-scream/i-scream_test.csv'
save_dir_prefix = './results/'
best_model_save_path = "./results/model/LSTM-102"

def confusion_matrix(filename):
    tp, tn, fp, fn = 0, 0, 0, 0 
    rows = []
    with open(filename, 'r') as f: 
        print("Reading {0}".format(filename))
        reader = csv.reader(f, delimiter=',')
        for row in reader:
            rows.append(row)
        print("{0} lines was read".format(len(rows)))

    for i in range(len(rows)):
        tp += rows[i].count('TP')
        tn += rows[i].count('TN')
        fp += rows[i].count('FP')
        fn += rows[i].count('FN')
    confusion_matrix = [tp, tn, fp, fn]
    print(confusion_matrix)
    return confusion_matrix


def write_csv(filename1, filename2, confusion_matrix): 
    # read the csv file
    rows1, rows2 = [], []
    with open(filename1, 'r') as f: 
        print("Reading {0}".format(filename1))
        reader = csv.reader(f, delimiter=',')
        for row in reader:
            rows1.append(row)
        print("{0} lines was read".format(len(rows1)))

    with open(filename2, 'r') as f: 
        print("Reading {0}".format(filename2))
        reader = csv.reader(f, delimiter=',')
        for row in reader:
            rows2.append(row)
        print("{0} lines was read".format(len(rows2)))

    with open('./results/test_results.csv', 'w', newline="") as f: 
        writer = csv.writer(f)
        for i in range(0, len(rows1), 3):
            writer.writerow(rows1[i])
            writer.writerow(rows1[i+1])
            writer.writerow(rows1[i+2])
            writer.writerow(rows2[i//3])
        writer.writerow(['TP : {}'.format(confusion_matrix[0])])
        writer.writerow(['TN : {}'.format(confusion_matrix[1])])
        writer.writerow(['FP : {}'.format(confusion_matrix[2])])
        writer.writerow(['FN : {}'.format(confusion_matrix[3])])


if __name__ == "__main__":
    config = tf.ConfigProto()
    config.gpu_options.allow_growth = True
    sess = tf.Session(config=config)
    
    data = DKTData(train_path, valid_path, test_path, batch_size=batch_size)
    data_train = data.train
    data_valid = data.valid
    data_test = data.test
    num_problems = data.num_problems

    dkt = DKT(sess, data_train, data_valid, data_test, num_problems, network_config,
              save_dir_prefix=save_dir_prefix,
              num_runs=num_runs, num_epochs=num_epochs,
              keep_prob=keep_prob, logging=True, save=True)
    
    dkt.model.build_graph()
    saver = tf.train.Saver()
    saver.restore(sess=sess, save_path=best_model_save_path)
    dkt.evaluate('test', makefile=True)
    
    info = confusion_matrix('./results/confusion_information_test.csv')
    write_csv('./data/i-scream/i-scream_test.csv', './results/confusion_information_test.csv', info)
    os.remove('./results/confusion_information_test.csv')
