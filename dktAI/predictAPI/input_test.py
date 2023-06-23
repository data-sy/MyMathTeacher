import csv


def read_data_from_csv(filename):
    # read the csv file
    rows = []
    with open(filename, 'r') as f:
        print("Reading {0}".format(filename))
        reader = csv.reader(f, delimiter=',')
        for row in reader:
            rows.append(row)
        print("{0} lines was read".format(len(rows)))

    # tuples stores the student answering sequence as
    # ([num_problems_answered], [problem_ids], [is_corrects])
    max_seq_length = 0
    # num_problems = 0
    num_problems = 1865

    tuples = []
    for i in range(0, len(rows), 3):
        # numbers of problem a student answered
        seq_length = len(rows[i + 1])

        # only keep student with at least 3 records.
        if seq_length < 3:
            continue

        problem_seq = rows[i + 1]
        correct_seq = rows[i + 2]

        invalid_ids_loc = [i for i, pid in enumerate(problem_seq) if pid == '']
        for invalid_loc in invalid_ids_loc:
            del problem_seq[invalid_loc]
            del correct_seq[invalid_loc]

        # convert the sequence from string to int.
        problem_seq = list(map(int, problem_seq))
        correct_seq = list(map(int, correct_seq))

        tup = (seq_length, problem_seq, correct_seq)
        tuples.append(tup)

        if max_seq_length < seq_length:
            max_seq_length = seq_length

        # pid = max(int(pid) for pid in problem_seq if pid != '')

        # if num_problems < pid:
        #     num_problems = pid

    print("max_num_problems_answered:", max_seq_length)
    print("num_problems:", num_problems)
    print("The number of data is {0}".format(len(tuples)))
    print("Finish reading data.")

    return tuples, num_problems, max_seq_length


test_path = './input_test_data.csv'

students, num_problems, max_seq_length = read_data_from_csv(test_path)
print(students)
print(num_problems)
print(max_seq_length)

# 학생 당 길이 3인 튜플 (문제 개수, 문제 시퀀스, 정오답 시퀀스)
# students : [(5, [12, 12, 12, 12, 12], [1, 1, 1, 1, 1]),
#             (3, [12, 12, 12], [0, 0, 0])]
# 문제 시퀀스
problem_seqs = [student[1] for student in students]
# 정오답 시퀀스
correct_seqs = [student[2] for student in students]

print(problem_seqs)
print(correct_seqs)







