<template>
  <div>
    <h1>회원가입 화면</h1>
    <button @click="signup">회원가입</button>
    <div>{{ data }}</div>
    <div v-if="error" style="color: red">{{ error.message }}</div>
  </div>
</template>

<script>
// 로그인화면에서 회원가입 누르면 dialog창 뜨는 게 이 페이지
// sakai 에서 src/views/pages/auth/Crud.vue 에서 new 버튼 누르면 dialog 나오는 거 사용하기
// 회원가입 버튼을 누르면 http://15.164.232.32:8080/api/v1/signup으로 post 보내고
import { ref } from "vue";
import { useApi } from "../../composables/api.js";

export default {
  setup() {
    const api = useApi();
    const data = ref(null);
    const error = ref(null);

    async function signup() {
      const requestData = {
        userEmail: "testtest@gmail.com",
        userPassword: "testtest",
        userName: "테스트테스트",
      };

      try {
        const response = await api.post("/signup", requestData);
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
      signup,
    };
  },
};
// 기존 로그인 화면으로 리다이렉트 (아니면 그냥 dialog창이 꺼지는 것)
</script>
