<template>
  <div>
    <h1>분석 결과 화면</h1>
    <div>{{ data }}</div>
    <div v-if="error" style="color: red">{{ error.message }}</div>
  </div>
</template>

<script>
// 이전 페이지에서 버튼 클릭 시 studentTestId가 넘어와서 여기서 사용되도록
    // https://chat.openai.com/share/35ae6734-ae2c-4386-9d70-c34b084d3f6a
// http://15.164.232.32:8080/api/v1/analysis/{studentTestId}로 get 보내고
// testDetail 컴포넌트 사용해서 화면에 학습지데이터 뿌려주고
// 오른쪽에는 분석 결과 뿌려주기
import { ref, onMounted } from "vue";
import { useApi } from "../composables/api.js";

export default {
  setup() {
    const api = useApi();
    const data = ref(null);
    const error = ref(null);

    // 데이터 불러오기
    async function analysisData() {
      const studentTestId = 1;
      try {
        const response = await api.get(`/analysis/${studentTestId}`);
        data.value = response;
        error.value = null;
      } catch (err) {
        console.error("데이터를 불러오는 중 에러 발생:", err);
        error.value = err;
      }
    }

    // 페이지 로드 시 데이터 불러오기
    onMounted(() => {
        analysisData();
    })

    return {
      data,
      error,
    };
  },
};
// [맞춤학습지 출제하기] 버튼을 클릭하면 
// studentTestId(혹은 다른 데이터들도 함께)를 가지고 PersonalView.vue로 이동
</script>
