# 텐서플로우 작동 테스트
import tensorflow as tf
print('## = ', tf.__version__)

# predict.py 작동 테스트
from predict import predict

input_data = [[[1, 0], [2, 1]]]
output_data = predict(input_data)
print(output_data[:10])
