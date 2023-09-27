<template>
    <div>
      <button @click="fetchData">데이터 불러오기</button>
      <button @click="createData">데이터 생성하기</button>
      <button @click="updateData">데이터 업데이트하기</button>
      <button @click="deleteData">데이터 삭제하기</button>
      <div>{{ data }}</div>
      <div v-if="error" style="color: red;">{{ error.message }}</div>
    </div>
</template>
  
<script>
  import { ref } from 'vue';
  import { useApi } from '../composables/api.js';
  
  export default {
    setup() {
      const api = useApi();
  
      // 데이터를 저장할 리액티브 변수 설정
      // 배열형태일 때
      const data = ref([]);
      // // 단일값 또는 객체 형태일 때
      // const data = ref(null);
      const error = ref(null);
  
      // 데이터 불러오기
      async function fetchData() {
        const teacherId = 3;
        try {
          const response = await api.get(`/students?teacherId=${teacherId}`);
          data.value = response;
          error.value = null;
        } catch (err) {
          console.error('데이터를 불러오는 중 에러 발생:', err);
          error.value = err;
        }
      }
  
      // 데이터 생성하기
      async function createData() {
        const requestData = {
          "studentName": "api테스트",
          "studentPhone": "01012345678",
          "studentBirthdate": "2000-01-01",
          "studentSchool": "",
          "studentComments": "",
          "teacherId": 4
        };
        try {
          const response = await api.post('/students', requestData);
          // 생성된 데이터를 데이터 배열에 추가
          data.value.push(response);
          // // 단일값일 때는 
          // data.value = response;
          error.value = null;
        } catch (err) {
          console.error('데이터 생성 중 에러 발생:', err);
          error.value = err;
        }
      }
  
      // 데이터 업데이트하기
      async function updateData() {
        const requestData = {
          "studentId": 5,
          "studentName": "업데이트",
          "studentPhone": "01012345678",
          "studentBirthdate": "2000-01-01",
          "studentSchool": "",
          "studentComments": "",
          "teacherId": 4
        };
        const studentId = 5; // 업데이트할 데이터의 ID
        try {
          const response = await api.put(`/students/${studentId}`, requestData);
          // 업데이트된 데이터를 데이터 배열에 반영
          const index = data.value.findIndex((item) => item.id === studentId);
          if (index !== -1) {
            data.value[index] = response;
          }
          // // 단일값일 때는 
          // data.value = response;
          error.value = null;
        } catch (err) {
          console.error('데이터 업데이트 중 에러 발생:', err);
          error.value = err;
        }
      }
  
      // 데이터 삭제하기
      async function deleteData() {
        const studentId = 5; // 삭제할 데이터의 ID
        try {
          // // 리스펀스 없나?
          // await api.del(`/students/${dataId}`);
          const response = await api.del(`/students/${studentId}`);
          // 데이터 배열에서 삭제
          data.value = data.value.filter((item) => item.id !== studentId);
          error.value = null;
        } catch (err) {
          console.error('데이터 삭제 중 에러 발생:', err);
          error.value = err;
        }
      }

      return {
        data,
        error,
        fetchData,
        createData,
        updateData,
        deleteData,
      };
    }
  };
</script>
