import json

from flask import Flask, request, jsonify
import requests

from predict import predict

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello world!'

@app.route('/analysis', methods=['POST'])
def analysis():

    # 스프링 서버에서 ai_input 받기
    spring_api_url = 'http://localhost:8080/ai-input'
    response = requests.get(spring_api_url, json=request.get_json())

    if response.status_code == 200:
        ai_input_response = response.json()
        student_test_id = ai_input_response['studentTestid']
        input_data = ai_input_response['answerCodeResponseList']

        # 진단
        output = predict(input_data)

        response_data = {
            "studentTestId": student_test_id,
            "probabilityList": output
        }
    else:
        return 'Failed to fetch data from Spring 1', 500

    # 스프링 서버로 ai_output 보내기
    spring_api_url = 'http://localhost:8080/ai-output'
    response = requests.post(spring_api_url, json=response_data)

    if response.status_code == 200:
        return jsonify(response_data), 200
    else:
        return 'Failed to fetch data from Spring 2', 500

if __name__ == '__main__':
    app.run()