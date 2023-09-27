<template>
      <div>
    <h1>학습지 목록, 학습지 상세보기 화면</h1>
    <div>{{ testsData }}</div>
    <button @click="testsDetail">학습지 한 행 클릭 시 학습지 상세보기</button>
    <div>{{ testsDetailData }}</div>
    <div v-if="error" style="color: red">{{ error.message }}</div>
  </div>
</template>
  
<script>
// http://15.164.232.32:8080/api/v1/tests로 전체 학습지 목록 get
// 학습지 행을 클릭했을 때 testId에 따른
// http://15.164.232.32:8080/api/v1/tests/{testId} 학습지 상세정보 get

import { ref, onMounted } from "vue";
import { useApi } from "../composables/api.js";

export default {
  setup() {
    const api = useApi();
    const testsData = ref([]);
    const testsDetailData = ref(null);
    const error = ref(null);

    // 학생 데이터 불러오기
    async function tests() {
      try {
        const response = await api.get('/tests');
        testsData.value = response;
        error.value = null;
      } catch (err) {
        console.error("데이터를 불러오는 중 에러 발생:", err);
        error.value = err;
      }
    }
    // 페이지 로드 시 데이터 불러오기
    onMounted(() => {
      tests();
    });

    // 학습지 데이터 불러오기
    async function testsDetail() {
      const testId = 5;
      try {
        const response = await api.get(`/tests/${testId}`);
        testsDetailData.value = response;
        error.value = null;
      } catch (err) {
        console.error("데이터를 불러오는 중 에러 발생:", err);
        error.value = err;
      }
    }

    return {
        testsData,
        testsDetailData,
      error,
      testsDetail,
    };
  },
};
// 리팩토링
// TestList, TestDetail 컴포넌트

</script>
