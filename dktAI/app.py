import json

from flask import Flask, request, jsonify
import requests

from predict import predict

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello world!'

@app.route('/test')
def aitest():
    input01 = [[1171, 1], [467, 1], [1703, 1], [1817, 1], [1698, 1], [623, 0], [1182, 0], [1614, 0], [396, 0],
               [1681, 0], [1564, 1], [461, 1], [782, 1], [593, 1], [1582, 1], [774, 0], [1660, 0], [1583, 0], [790, 0],
               [1531, 0]]
    input02 = [[1171, 0], [467, 1], [1703, 1], [1817, 1], [1698, 1], [623, 1], [1182, 1], [1614, 1], [396, 1],
               [1681, 1], [1564, 1], [461, 1], [782, 1], [593, 1], [1582, 1], [774, 1], [1660, 1], [1583, 0], [790, 0],
               [1531, 1]]
    input03 = [[1207, 1], [1218, 1], [1267, 1], [1230, 0], [1224, 0], [1206, 1], [1232, 1], [1216, 1], [1245, 0],
               [1247, 0], [1285, 1], [1255, 1], [1240, 1], [1283, 0], [1258, 0], [1226, 1], [1219, 1], [1268, 1],
               [1238, 0], [1282, 0]]
    input04 = [[1505, 1], [1509, 1], [1406, 1], [1405, 1], [1439, 0], [1517, 1], [1506, 1], [1402, 1], [1510, 1],
               [1523, 0], [1526, 1], [1428, 1], [1383, 1], [1396, 1], [1397, 0], [1395, 0], [1404, 0], [1401, 0],
               [1389, 0], [1384, 0]]
    input_data = [input01, input02, input03, input04]
    output = predict(input_data)
    response_data = {
        "studentTestId": 4,
        "probabilityList": output
    }
    return jsonify(response_data), 200

@app.route('/analysis', methods=['POST'])
def analysis():
    spring_api_url = 'http://13.125.250.204:8080/predict'

    # 스프링 서버에서 ai_input 받기
    response_get = requests.get(spring_api_url, json=request.get_json())

    if response_get.status_code == 200:
        ai_input_response = response_get.json()
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
    response_post = requests.post(spring_api_url, json=response_data)

    if response_post.status_code == 200:
        return jsonify(response_data), 200
    else:
        return 'Failed to fetch data from Spring 2', 500

if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0')