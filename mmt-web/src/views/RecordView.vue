<template>
  <div>
    <h1>기록 화면</h1>
    <div>{{ data }}</div>
    <div v-if="error" style="color: red">{{ error.message }}</div>
  </div>
</template>

<script>
// 이전 화면에서 받은 testId를 사용해서
// http://15.164.232.32:8080/api/v1/tests/{testId} get 결과를 화면에 뿌려주기
// 오른쪽에 답안 입력받는 폼

import { ref, onMounted } from "vue";
import { useApi } from "../composables/api.js";

export default {
  setup() {
    const api = useApi();
    const data = ref(null);
    const error = ref(null);

    // 데이터 불러오기
    async function testData() {
      const testId = 5;
      try {
        const response = await api.get(`/tests/${testId}`);
        data.value = response;
        error.value = null;
      } catch (err) {
        console.error("데이터를 불러오는 중 에러 발생:", err);
        error.value = err;
      }
    }

    // 페이지 로드 시 데이터 불러오기
    onMounted(() => {
      testData();
    });

    return {
      data,
      error,
    };
  },
};

// 리팩토링
// TestDetail 컴포넌트 가져오기, form으로 정오답 받기
</script>
