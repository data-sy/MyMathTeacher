import json

from flask import Flask, request, jsonify
import requests
from flask_cors import CORS

from predict import predict

app = Flask(__name__)
CORS(app, resources={r"/*": {"origins": "*"}})
# CORS(app, resources={r"/api/v1/*": {"origins": "http://localhost:3000,http://15.164.232.32:8080"}})

@app.route('/')
def hello_world():
    return 'Hello world!'

@app.route('/corstest')
def corstest():
    spring_api_url = 'http://15.164.232.32:8080/api/v1/hello'
    response = requests.get(spring_api_url)
    if response.status_code == 200:
        response_text = response.text
        print(f"Response from Spring server: {response_text}")
        return jsonify(response_text), 200
    else:
        print(f"Failed to retrieve data from Spring server. Status code: {response.status_code}")
        return 'Failed to fetch data from Spring 1', 500

@app.route('/aitest')
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
    output_data = predict(input_data)
    response_data = {
        "studentTestId": 4,
        "probabilityList": output_data
    }
    return jsonify(response_data), 200

@app.route('/scenario')
def scenario():
    # # request body에서 정오답 시퀀스 추출
    request_data = request.get_json()
    scenario_case = request_data.get('scenarioCase')
    input_data = request_data.get('inputData')

    # 데이터 늘리는 여러 버전으로 실행
    pro_list = []
    for i in [1, 10, 100, 500] :
        duplicated_list = [sublist for sublist in input_data for _ in range(i)]

        # ai실행
        output_data = predict(duplicated_list)
        pro_list.append([i, output_data])

    response_data = {
        "scenarioCase": scenario_case,
        "probabilityList": pro_list
    }

    # 스프링 서버로 output 보내기
    spring_api_url = 'http://127.0.0.1:8080/scenario'
    response_post = requests.post(spring_api_url, json=response_data)

    if response_post.status_code == 200:
        return jsonify(response_data), 200
    else:
        return 'Failed to fetch data from Spring', 500

@app.route('/api/v1/analysis', methods=['POST'])
def analysis():
    # 토큰
    jwt_token = request.headers.get('Authorization')
    headers = {
        "Authorization": jwt_token,
        "Content-Type": "application/json"
    }

    # request body에서 studentTestId 추출
    request_data = request.get_json()
    stu_test_id = request_data.get('studentTestId')

    spring_api_url = 'http://127.0.0.1:8080/api/v1/predict/'+str(stu_test_id)

    # 스프링 서버에서 ai_input 받기
    response_get = requests.get(spring_api_url, headers=headers)

    if response_get.status_code == 200:
        ai_input_response = response_get.json()
        student_test_id = ai_input_response['studentTestId']
        input_data = [ai_input_response['answerCodeResponseList']]

        # 진단
        output = predict(input_data)

        response_data = {
            "studentTestId": student_test_id,
            "probabilityList": output
        }

    else:
        return 'Failed to fetch data from Spring 1', 500


    spring_api_url2 = 'http://127.0.0.1:8080/api/v1/predict'

    # 스프링 서버로 ai_output 보내기
    response_post = requests.post(spring_api_url2, json=response_data, headers=headers)

    if response_post.status_code == 200:
        return jsonify(response_data), 200
    else:
        return 'Failed to fetch data from Spring 2', 500

if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0', port=5000)
