import tensorflow as tf
tf.compat.v1.disable_eager_execution() #텐서플로우 v2 에서도 실행 가능하게 하기
#from tensorflow_core.python.saved_model import tag_constants


'''
- 미적분 실제 데이터
- 정ㅇㅇ
'''

# 모델 경로
model_path = '../savedmodel/'

# 예측
with tf.compat.v1.Session(graph=tf.Graph()) as sess:
  model = tf.compat.v1.saved_model.loader.load(sess, [tf.saved_model.SERVING], model_path)

  input = [[1712, 0], [1715, 1], [1724, 1], [1736, 1], [1735, 1],
           [1718, 1], [1730, 1], [1737, 1], [1751, 1], [1724, 1], [1715, 1]
           [1740, 0], [1726, 1], [471, 1], [1740, 0], [1740, 0], [1742, 0], [1719, 1], [1570, 1],
           [1732, 0], # 1학기 중간
           [1751, 1], [1752, 1], [1654, 1], [1758, 1], [1759, 1],
           [1762, 1], [1761, 1], [1760, 1], [1673, 1], [1770, 1], [1767, 1],
           [1678, 0], [1772, 0], [1753, 1], [1758, 1] # 1학기 기말
           ]

  input_data = [input]


  input_tensor = sess.graph.get_tensor_by_name("Input_index:0")
  output_tensor = sess.graph.get_tensor_by_name("strided_slice_2:0")

  # 모델 실행
  output = sess.run(output_tensor, feed_dict={input_tensor: input_data})

  # 마지막 학습지에 대해 판단
  output = output[-1]

  # 출력 결과 사용
  for pro in output:
    print(pro, end=', ')
