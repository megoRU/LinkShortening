function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var messageApi = Vue.resource('/url{/id}');

Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function () {
        return {
            text: '',
            id: ''
        }
    },
    watch: {
        messageAttr: function (newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },

    template:
        '<div class="input-group mb-3">' +
        '<label id="text-input"></label><input id="text-input" class="form-control" placeholder="Enter the link" type="text" v-model="text" value="">' +
        '<button id="save-task" type="button" class="btn btn-outline-primary" @click="save">Send</button>' +
        '</div>',
    methods: {
        save: function () {
            const message = {text: this.text};
            if (message.text === "") {
                return
            }
            messageApi.save({}, message).then(result =>
                result.json().then(data => {
                    this.messages.push(data);
                    this.text = ''
                })
            )
        }
    }
});

Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages'],
    template:
        '<div class="a" style="">' +
        '<a :href="\'http://localhost:8080/r/\' + message.destinationUrl ">  http://localhost:8080/r/{{ message.destinationUrl }}</a> -> {{ message.sourceUrl }}' +
        '   <button type="button" class="btn btn-danger" @click="del">Удалить</button>' +
        '</div>',
    methods: {
        del: function () {
            messageApi.remove({id: this.message.id}).then(result => {
                if (result.ok) {
                    this.messages.splice(this.messages.indexOf(this.message), 1)
                }
            })
        }
    }
});

Vue.component('messages-list', {
    props: ['messages'],
    data: function () {
        return {
            message: null
        }
    },
    template:
        '<div>' +
        '<message-form :messages="messages" :messageAttr="message" />' +
        '<message-row v-for="message in messages" :key="message.id" :message="message" ' +
        ':editMethod="editMethod" :messages="messages" />' +
        '</div>',
    created: function () {
        messageApi.get().then(result =>
            result.json().then(data =>
                data.forEach(message => this.messages.push(message))
            )
        )
    },
    methods: {
        editMethod: function (message) {
            this.message = message;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    }
});