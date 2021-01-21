window.axios.interceptors.response.use(response => {
    return response;
}, error => {
    return Promise.reject(error.response)
});
window._vm = new Vue({
    el: '#app',
    data: function () {
        return {

        }
    },
    computed: {

    },
    methods: {
        test() {
            for (let i = 0; i < 10; i++) {
                window.axios.get('api/v1/hi/' + i).then(res => {
                    console.log(res);
                })
            }
        }
    },
    mounted() {

    }
});
