const { createApp, ref } = Vue
const API_URL = 'http://localhost:8080';
const URL = (endpoint) => API_URL + endpoint;

createApp({
    data() {
        return {
            name: '',
            employees: []
        }
    },

    mounted() {
        this.list();
    },

    methods: {
        list() {
            fetch(URL('/employee'))
                .then(response => response.json())
                .then(data => {
                    this.employees = data._embedded.employee;
                })
        },

        add(event) {
            event.preventDefault();

            fetch(
                URL('/employee'),
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
