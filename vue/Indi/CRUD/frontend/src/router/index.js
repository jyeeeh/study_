import { createRouter, createWebHistory } from 'vue-router'
import UserList from '@/components/UserList.vue'
import CreateUser from '@/components/CreateUser.vue'
import UpdateUser from '@/components/UpdateUser.vue'

const routes = [
    // 추가사항
    { path: '/', component: UserList },
    { path: '/create', component: CreateUser },
    { path: '/update/:id', component: UpdateUser },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router