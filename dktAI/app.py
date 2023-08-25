from flask import Flask, request, jsonify
import requests

from predict import predict

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello world!'

@app.route('/analysis', methods=['POST'])
def analysis():

    spring_api_url = 'http://localhost:8080/ai-input'
    response = requests.get(spring_api_url, json=request.get_json())

    if response.status_code == 200:
        ai_input_response = response.json()
        student_test_id = ai_input_response['studentTestid']
        input_data = ai_input_response['answerCodeResponseList']

        print(ai_input_response)
        print(input_data)

        # 진단
        output = predict(input_data)

        # 임시 결과

        test_data = {
            'studentTestId': student_test_id,
            'probabilityList': output
        }

        return jsonify(test_data), 200
    else:
        return 'Failed to fetch data from Spring', 500

if __name__ == '__main__':
    app.run()