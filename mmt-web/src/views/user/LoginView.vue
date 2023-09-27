<template>
  <div>
    <h1>로그인 화면</h1>
    <button @click="login">로그인</button>
    <div>{{ data }}</div>
    <div v-if="error" style="color: red">{{ error.message }}</div>
  </div>
</template>

<script>
// sakai 에서 src/views/pages/auth/Login.vue 사용하기
// 로그인 버튼을 누르면 http://15.164.232.32:8080/api/v1/authenticate로 post 보내고
import { ref } from "vue";
import { useApi } from "../../composables/api.js";

export default {
  setup() {
    const api = useApi();
    const data = ref(null);
    const error = ref(null);

    async function login() {
      const requestData = {
        userEmail: "test@gmail.com",
        userPassword: "test",
      };
      try {
        const response = await api.post("/authenticate", requestData);
        data.value = response;
        error.value = null;
      } catch (err) {
        console.error("데이터 생성 중 에러 발생:", err);
        error.value = err;
      }
    }
    return {
      data,
      error,
      login,
    };
  },
};
// 홈화면으로 리다이렉트
</script>
