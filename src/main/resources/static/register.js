export const Register = Vue.component('register', {
    template: `
<div class="d-flex flex-column justify-content-center">
    <main class="form-signin w-600">
      <form @submit.prevent="register">
        <i class="bi bi-person-plus" style="color: #ecff00;"></i>
        <h1 class="h4 mb-3 fw-light" style="color: #ecff00;">Register</h1>

        <div class="form-floating mb-3">
          <input v-model="firstname" type="text" class="form-control" id="floatingFirstname" placeholder="First Name">
          <label for="floatingFirstname">First Name</label>
        </div>
        <div class="form-floating mb-3">
          <input v-model="lastname" type="text" class="form-control" id="floatingLastname" placeholder="Last Name">
          <label for="floatingLastname">Last Name</label>
        </div>
        <div class="form-floating mb-3">
          <input v-model="email" type="email" class="form-control" id="floatingEmail" placeholder="name@example.com">
          <label for="floatingEmail">Email address</label>
        </div>
        <div class="form-floating mb-3">
          <input v-model="webSite" type="text" class="form-control" id="floatingWebSite" placeholder="Website">
          <label for="floatingWebSite">Website</label>
        </div>
        <div class="form-floating mb-3">
           <input v-model="birthDay" type="date" class="form-control" id="floatingBirthDay" placeholder="YYYY-M-D">
          <label for="floatingBirthDay">Birth Day (YYYY-M-D)</label>
        </div>
        <div class="form-floating mb-3">
          <input v-model="password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
          <label for="floatingPassword">Password</label>
        </div>

        <button class="btn btn-success w-100 py-2" type="submit">Register</button>
      </form>
      <div v-if="showAlert" class="alert alert-success" role="alert">
        Registration successful.
      </div>
    </main>
  </div>
   
        
  `,
    data() {
        return {
            firstname: '',
            lastname: '',
            email: '',
            webSite: '',
            birthDay: '',
            password: '',
            showAlert: false,
        };
    },
    methods: {
        async register() {
            try {
                const response = await fetch('/api/v1/auth/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        firstname: this.firstname,
                        lastname: this.lastname,
                        email: this.email,
                        webSite: this.webSite,
                        birthDay: this.birthDay,
                        password: this.password,
                        role: 'USER',
                    }),
                });

                if (!response.ok) {
                    throw new Error('Registration failed');
                }


                this.showAlert = true;
                setTimeout(() => {
                    this.showAlert = false;
                }, 3000);

                window.location.href = '#/login';

            } catch (error) {
                console.error('Error during registration:', error.message);
            }
        },
    }
});
