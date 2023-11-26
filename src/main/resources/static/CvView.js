
export const CvView = Vue.component('cv-view', {
    props: ['view'],
    template: `
<div class="cv-view-container">
    <div class="row mb-3">
      <div class="col">
        <p class="section-header">Personal Information</p>
        <hr class="section-divider">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <p class="personal-info"> NAME : {{ view.firstName }} {{ view.lastName }}</p>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        AGE : <p class="personal-info fs-6 fw-light fst-italic">{{ calculateAge(view.birthDay) }}</p>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <p class="personal-info fs-6"> EMAIL : {{ view.email }}</p>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <p class="personal-info fs-6">WEBSITE : {{ view.webSite }}</p>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <p class="section-header">Experiences</p>
        <hr class="experience-divider">
      </div>
    </div>
    <div v-for="(experience, index) in view.curriculumVitae.experiences" :key="index">
      <div class="row mb-3">
        <div class="col">
        
          <p class="experience-info fs-6">
            ¤ {{ experience.title }} : {{ experience.nature }} .
          </p>
          <p class="experience-info fs-6">
            {{ experience.year }}.
          </p>
          <p class="experience-info fs-6">
            DESCRIPTION : 
            {{ experience.description }}.
          </p>
          <p class="experience-info fs-6">
            WEBSITE : <a>{{ experience.webSite }}.</a>
          </p>
          <hr class="experience-divider">
        </div>
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
        }
    }
});



