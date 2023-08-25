import tensorflow as tf
tf.compat.v1.disable_eager_execution() #텐서플로우 v2 에서도 실행 가능하게 하기
from tensorflow_core.python.saved_model import tag_constants


'''
- 다영이의 수능공통, 미적분, 기하 학습지에 대한 확률
- student_test_id = 7 -> 3, 4, 5, 7
'''

# 모델 경로
model_path = '../savedmodel/'

# 예측
with tf.compat.v1.Session(graph=tf.Graph()) as sess:
  model = tf.compat.v1.saved_model.loader.load(sess, [tag_constants.SERVING], model_path)

  input01 = [[1171,1],[467,1],[1703,1],[1817,1],[1698,1],[623,0],[1182,0],[1614,0],[396,0],[1681,0],[1564,1],[461,1],[782,1],[593,1],[1582,1],[774,0],[1660,0],[1583,0],[790,0],[1531,0]]
  input02 = [[1171,0],[467,1],[1703,1],[1817,1],[1698,1],[623,1],[1182,1],[1614,1],[396,1],[1681,1],[1564,1],[461,1],[782,1],[593,1],[1582,1],[774,1],[1660,1],[1583,0],[790,0],[1531,1]]
  input03 = [[1207,1],[1218,1],[1267,1],[1230,0],[1224,0],[1206,1],[1232,1],[1216,1],[1245,0],[1247,0],[1285,1],[1255,1],[1240,1],[1283,0],[1258,0],[1226,1],[1219,1],[1268,1],[1238,0],[1282,0]]
  input04 = [[1505,1],[1509,1],[1406,1],[1405,1],[1439,0],[1517,1],[1506,1],[1402,1],[1510,1],[1523,0],[1526,1],[1428,1],[1383,1],[1396,1],[1397,0],[1395,0],[1404,0],[1401,0],[1389,0],[1384,0]]
  input_data = [input01, input02, input03, input04]

  input_tensor = sess.graph.get_tensor_by_name("Input_index:0")
  output_tensor = sess.graph.get_tensor_by_name("strided_slice_2:0")

  # 모델 실행
  output = sess.run(output_tensor, feed_dict={input_tensor: input_data})

  # 마지막 학습지에 대해 판단
  output = output[-1]

  # 출력 결과 사용
  for pro in output:
    print(pro, end=', ')
