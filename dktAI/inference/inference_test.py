import tensorflow as tf
tf.compat.v1.disable_eager_execution() #텐서플로우 v2 에서도 실행 가능하게 하기
from tensorflow_core.python.saved_model import tag_constants
'''
- 시나리오 1 : 정답률과 예측한 확률값이 유사하게 나오는지
    학생1 : 10번 문항 0% 정답
    학생2 : 50번 문항 20% 정답 + 맨 앞, 섞어, 맨 뒤
    학생3 : 100번 문항 50% 정답 + 맨 앞, 퐁당퐁당
    학생5 : 1000번 문항 80% 정답 + 맨 앞, 섞어, 맨 뒤
    학생6 : 1000번 문항 100% 정답
- 시나리오 2 : 여러 문항 섞어서 input
    지식 간의 관계를 사용해야 하므로 나중에 DB 연결해서 spring에서 진행
'''
# 모델 경로
model_path = '../savedmodel/'

# 예측
with tf.compat.v1.Session(graph=tf.Graph()) as sess:
  model = tf.compat.v1.saved_model.loader.load(sess, [tag_constants.SERVING], model_path)

  # 실제 서비스에서는 1명에 1학습지 이므로 (1, 문항수, 2) 꼴이 될 거야
  # input_data : [[스킬id, 정오답결과], [스킬id, 정오답결과], ..., [스킬id, 정오답결과], [스킬id, 정오답결과]]
  # 여기서는 시나리오 별 테스트를 위해 여러 학습자를 담음 (학습자수, 문항수, 2), 전처리 편의상 길이도 맞춤 (10문항)
  stu1 = [[10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0], [10, 0]]
  stu2_1 = [[50, 1], [50, 1], [50, 0], [50, 0], [50, 0], [50, 0], [50, 0], [50, 0], [50, 0], [50, 0]]
  stu2_2 = [[50, 0], [50, 0], [50, 0], [50, 0], [50, 0], [50, 1], [50, 0], [50, 0], [50, 0], [50, 1]]
  stu2_3 = [[50, 0], [50, 0], [50, 0], [50, 0], [50, 0], [50, 0], [50, 0], [50, 0], [50, 1], [50, 1]]
  stu3_1 = [[100, 1], [100, 1], [100, 1], [100, 1], [100, 1], [100, 0], [100, 0], [100, 0], [100, 0], [100, 0]]
  stu3_2 = [[100, 1], [100, 0], [100, 1], [100, 0], [100, 1],[100, 0], [100, 1], [100, 0], [100, 1], [100, 0]]
  stu4_1 = [[1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 0], [1000, 0],[1000, 0]]
  stu4_2 = [[1000, 1], [1000, 0], [1000, 1], [1000, 0], [1000, 1], [1000, 1], [1000, 0], [1000, 1], [1000, 1],[1000, 1]]
  stu4_3 = [[1000, 0], [1000, 0], [1000, 0], [1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 1],[1000, 1]]
  stu5 = [[1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 1], [1000, 1]]
  input_data = [stu1, stu2_1, stu2_2, stu2_3, stu3_1, stu3_2, stu4_1, stu4_2, stu4_3, stu5]

  # # # 입력 텐서, 출력 텐서 정보 확인
  # print(model.signature_def[tf.compat.v1.saved_model.signature_constants.DEFAULT_SERVING_SIGNATURE_DEF_KEY].inputs)
  # print(model.signature_def[tf.compat.v1.saved_model.signature_constants.DEFAULT_SERVING_SIGNATURE_DEF_KEY].outputs)
  # 여기서 name 알 수 있음

  # name으로 텐서 찾기
  input_tensor = sess.graph.get_tensor_by_name("Input_index:0")
  output_tensor = sess.graph.get_tensor_by_name("strided_slice_2:0")

  # # 입력 텐서, 출력 텐서의 데이터 유형, 형상 확인
  # print("입력 텐서의 데이터 유형 : ", input_tensor.dtype) # <dtype: 'int32'>
  # print("출력 텐서의 데이터 유형 : ", output_tensor.dtype) # <dtype: 'float32'>
  # print("입력 텐서의 쉐입 : ", input_tensor.shape) # (?, ?, 2)
  # print("출력 텐서의 쉐입 : ", output_tensor.shape) # (?, 1865)

  # 모델 실행
  output = sess.run(output_tensor, feed_dict={input_tensor: input_data})

  # # 출력 결과 확인
  # print(output.ndim) # 2차원
  # print(output.shape) # (1, 1865) = ( 학습지 1회, 스킬id 1865) => 이 시험지를 푼 학생이 스킬id를 맞출 확률
  # print(output.size)
  # print(output.dtype)

  # # 실제 서비스에서는 리스트 한 꺼풀 벗기기
  # output = output[0]

  # 출력 결과 분석
  print(" 학생1 : 10번 문항 0% 정답 : ", output[0][9])
  print(" 학생2_1 : 50번 문항 20% 정답 맨 앞 : ", output[1][49])
  print(" 학생2_2 : 50번 문항 20% 정답 섞어 : ", output[2][49])
  print(" 학생2_3 : 50번 문항 20% 정답 맨 뒤 : ", output[3][49])
  print(" 학생3_1 : 100번 문항 50% 정답 : ", output[4][99])
  print(" 학생3_2 : 100번 문항 퐁당퐁당 50% 정답 : ", output[5][99])
  print(" 학생4_1 : 1000번 문항 70% 정답 맨 앞 : ", output[6][999])
  print(" 학생4_2 : 1000번 문항 70% 정답 섞어 : ", output[7][999])
  print(" 학생4_3 : 1000번 문항 70% 정답 맨 뒤 : ", output[8][999])
  print(" 학생5 : 1000번 문항 100% 정답 : ", output[9][999])
  # 가장 최근에 푼 문항의 정오답 확률이 영향 많이 미침
  # 다 틀렸어도 50% 나오고 다 맞았어도 80% 나오네..
    # 문항IRT에도 영향을 받는 걸까? 문항 자체가 가지는 난이도가 있을 수 있으니..

  # 1~1865 모든 예측값이 의미 있진 않아
    # 관계 없는 문항 간에는 결과값이 학생의 문항정오답 확률이라고는 말할 수 없어.

  # 정답률 높은 학생의 경우에는 관계 있는 문항도 확률 높을 가능성 있음 (낮은 건 의미 없을 수 있음)
  # 학생 5에 대해 정오답예측이 70% 이상인 것들만 모아서 보자
  arr = output[9]
  pro_7up = []
  id_7up = []
  for i in range(len(arr)):
    if arr[i] > 0.7 :
      id_7up.append(i+1)
      pro_7up.append(arr[i])
  print("id_7up : ", id_7up)
  print("pro_7up : ", pro_7up)

  # 정답률 낮은 학생의 경우에는 높은 것도 인과관계 없을 수 있음 (관계 있는 문항 필터링 필수)
  # 즉!!!!!!!!! 지금으로서는 테스트에 한계 있고, 실제 관계망을 사용해서 필터링 해야 해

