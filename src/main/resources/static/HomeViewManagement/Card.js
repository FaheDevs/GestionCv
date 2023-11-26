
export const Card =Vue.component('card', {
    props: ['value'],
    template: `
  <div class="card mb-3">
  <div class="card-header fs-4 fw-bold">
    {{value.firstName}} {{value.lastName}}
  </div>
  <div class="card-body">
    <p class="card-title fs-6 fw-light fst-italic"> age : {{calculateAge(value.birthDay)}}</p>
    <div v-for="(experience, index) in value.curriculumVitae.experiences.slice(0, 3)" :key="index">
          <p class="card-text fs-6">
            - {{ experience.title }} : {{ experience.nature }} 
          </p>
    </div>
    <p class="card-text fs-6">
      - Website: {{ value.webSite }}
    </p>
    <p class="card-text fs-6">
      - Email: {{ value.email }}
    </p>
   <div class="d-flex justify-content-end">
      <button @click="showCurriculumVitae" class="btn btn-outline-dark fs-6">Show CurriculumVitae</button>
    </div>
  </div>
</div>
  `,
    methods:{
        calculateAge(birthDay) {
            const today = new Date();
            const birthDate = new Date(birthDay);

            let age = today.getFullYear() - birthDate.getFullYear();
            const monthDiff = today.getMonth() - birthDate.getMonth();

            if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
                age--;
            }

            return age;
        },
        showCurriculumVitae() {
            // Emit the 'show-cv' event with the user's data
            this.$emit('show-cv', this.value);
            console.log("sent", this.value)


        }
    }
});



