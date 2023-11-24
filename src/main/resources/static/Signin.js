export const login = Vue.component('login', {
    template: `
<div  class="d-flex flex-column justify-content-center">
<main class="form-signin w-600 ">
      <form @submit.prevent="signIn">
        <i class="bi bi-box-arrow-in-right" style="color: yellow;"></i>
        <h1 class="h4 mb-3 fw-light" style="color: yellow;"> Sign in</h1>
    
        <div class="form-floating">
          <input v-model="email" type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
          <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating">
          <input v-model="password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
          <label for="floatingPassword">Password</label>
        </div>
                <button class="btn btn-primary w-100 py-2" type="submit" >Sign in</button>
        <div class="mt-3">
            <a href="#" class="text-decoration-none">Forgot Password?</a>
        </div>
      </form>
      
</main>
    <div v-if="showAlert" class="alert alert-success" role="alert">
          Authentification successful .
        </div>
</div>
   
        
  `,
    data() {
        return {
            email: '',
            password: '',
            showAlert: false
        };
    },
    emits:["is-logged-in", "email"],
    methods: {
        async signIn() {
            try {
                const response = await fetch('http://localhost:8080/api/v1/auth/authenticate', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        email: this.email,
                        password: this.password
                    })
                });

                if (!response.ok) {
                    throw new Error('Authentication failed');
                }

                const data = await response.json();
                this.$emit('is-logged-in', true);
                this.$emit('send-email', this.email);
                localStorage.setItem('authToken', data.access_token);
                localStorage.setItem('refreshToken', data.refresh_token);

                this.showAlert = true;
                setTimeout(() => {
                    this.showAlert = false;
                }, 5000);

                window.location.href='#/dashboard'

            } catch (error) {
                // console.error('Error during authentication:', error.message);
            }
        }
    }
});
