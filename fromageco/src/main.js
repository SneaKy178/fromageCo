import App from "./App.vue";
import router from "./router";
import {createApp} from 'vue'

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.css'

const app = createApp(App)
app.use(router)
app.mount('#app')

