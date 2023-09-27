<template>
  <div>
    <h1>학생 목록, 학습지 목록 화면</h1>
    <div>{{ studentsData }}</div>
    <button @click="tests">학생 한 행 클릭 시 학습지 목록</button>
    <div>{{ testsData }}</div>
    <div v-if="error" style="color: red">{{ error.message }}</div>
  </div>
</template>

<script>
// 로그인한 userId(teacherId로 사용 돼)에 대해
// http://15.164.232.32:8080/api/v1/students?teacherId=3 로 get
// 왼쪽에 학생 목록 띄우고
// 학생 행을 클릭했을 때 studentId에 따른
// http://15.164.232.32:8080/api/v1/tests/student?studentId=2 로 get
// 오른쪽에 학습지 목록 뜨도록

import { ref, onMounted } from "vue";
import { useApi } from "../composables/api.js";

export default {
  setup() {
    const api = useApi();
    const studentsData = ref([]);
    const testsData = ref(null);
    const error = ref(null);

    // 학생 데이터 불러오기
    async function students() {
      const teacherId = 3;
      try {
        const response = await api.get(`/students?teacherId=${teacherId}`);
        studentsData.value = response;
        error.value = null;
      } catch (err) {
        console.error("데이터를 불러오는 중 에러 발생:", err);
        error.value = err;
      }
    }
    // 페이지 로드 시 데이터 불러오기
    onMounted(() => {
      students();
    });

    // 학습지 데이터 불러오기
    async function tests() {
      const studentId = 2;
      try {
        const response = await api.get(`/tests/student?studentId=${studentId}`);
        testsData.value = response;
        error.value = null;
      } catch (err) {
        console.error("데이터를 불러오는 중 에러 발생:", err);
        error.value = err;
      }
    }

    return {
      studentsData,
      testsData,
      error,
      tests,
    };
  },
};

// 리팩토링
// StudentList, TestList 컴포넌트
// 기록에서는 기록x 인 것만 클릭 가능
// 맞춤에서는 기록ㅇ 인 것만 클릭 가능
</script>
