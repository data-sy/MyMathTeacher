from flask import Flask, request, jsonify
import requests

# 다 한 뒤에 cors 붙여보기

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello world!'

@app.route('/connect-test', methods=['POST'])
def connect_test():
    # {
    #     "studentTestId": 7
    # }
    param = request.get_json()
    print(param)

    spring_api_url = 'http://localhost:8080/ai-input'
    response = requests.get(spring_api_url, json=param)

    if response.status_code == 200:
        ai_input_response = response.json()
        print(ai_input_response)

        # 데이터 전처리

        # 진단

        # 임시 결과
        test_data = {
            'studentTestId': 7,
            'probabilityList': [0.2, 0.4, 0.6, 0.8]
        }

        return jsonify(test_data), 200
    else:
        return 'Failed to fetch data from Spring', 500

if __name__ == '__main__':
    app.run()