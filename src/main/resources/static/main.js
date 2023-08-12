const { createApp, ref } = Vue
const API_URL = 'http://localhost:8080';
const endpoint = window.location.hash.substr(1);
const URL = () => API_URL + '/' + endpoint;

createApp({
    data() {
        return {
            endpoint: endpoint,
            name: '',
            items: []
        }
    },

    mounted() {
        this.list();
    },

    methods: {
        list() {
            fetch(URL())
                .then(response => response.json())
                .then(data => {
                    this.items = data._embedded[endpoint];
                })
        },

        add(event) {
            event.preventDefault();

            fetch(
                URL(),
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        name: this.name
                    })
                }
            ).then(response => {
                this.name = '';
                this.list();
            })
        },

        update(employee) {
            fetch(
                employee._links.self.href,
                {
                    method: 'PATCH',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        name: employee.name
                    })
                }
            ).then(response => {
                this.list();
            })
        },

        remove(employee) {
            fetch(
                employee._links.self.href,
                {
                    method: 'DELETE'
                }
            ).then(response => {
                this.list();
            })
        }
    }
}).mount('#app')
