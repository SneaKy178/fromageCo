import { reactive } from "vue";

const state = reactive({
    courriel: "",
    role: "",
    isLoggedIn: false,
})

export default { state }