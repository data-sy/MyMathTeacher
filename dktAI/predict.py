import tensorflow as tf
tf.compat.v1.disable_eager_execution()

def predict(input_data):
  model_path = 'savedmodel/'

  with tf.compat.v1.Session(graph=tf.Graph()) as sess:
    model = tf.compat.v1.saved_model.loader.load(sess, [tf.saved_model.SERVING], model_path)

    input_tensor = sess.graph.get_tensor_by_name("Input_index:0")
    output_tensor = sess.graph.get_tensor_by_name("strided_slice_2:0")

    output = sess.run(output_tensor, feed_dict={input_tensor: input_data})

    # 마지막 학습지에 대해 판단 & ndarray -> list
    output = output[-1].tolist()

  return output
