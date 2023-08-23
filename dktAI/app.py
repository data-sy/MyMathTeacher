from flask import Flask, request

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello world!'

@app.route('/analysis', methods=['GET'])
def inference():
    param = request.get_json()
    student_test_id = param['stduentTestId']
    answer_code_response_list = param['answerCodeResponseList']

    # 우선 여기에 쭉 쓰고 리팩토링 (메서드로 만들기, 적절한 위치로 옮기기)

    # 데이터 전처리
    input_data = []
    for test in answer_code_response_list :
        input = []
        for item in test :
            # 순서를 정확히 해야 하므로 values 사용 안 함
            dic_to_list = [item['skillId'], item['answerCode']]
            input.append(dic_to_list)
        input_data.append(input)

    # 진단하는 기능 넣기

    # test용
    test_data = {
        'studentTestId': 7,
        'probabilityList': [0.2, 0.4, 0.6, 0.8]
    }
    return test_data

if __name__ == '__main__':
    app.run()