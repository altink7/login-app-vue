import { createApp } from 'vue'
import App from './App.vue'
import vue3GoogleLogin from 'vue3-google-login'
import 'bootstrap/dist/css/bootstrap.css';

const app = createApp(App);
app.use(vue3GoogleLogin, { clientId: '102228032768-b6b40gt5l4m8mlruc87sbq7rite9jltu.apps.googleusercontent.com' });

app.mount('#app')
