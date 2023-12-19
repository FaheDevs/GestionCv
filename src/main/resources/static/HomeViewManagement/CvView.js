
export const CvView = Vue.component('cv-view', {
    props: ['view'],
    template: `
<div class="cv-view-container">
    <div class="header-section">
      <p class="section-header">Personal Information</p>
      <hr class="section-divider">
    </div>

    <!-- Personal Information -->
    <div class="personal-section">
      <p class="personal-info">NAME: {{ view.firstName }} {{ view.lastName }}</p>
      <p class="personal-info">AGE: {{ calculateAge(view.birthDay) }}</p>
      <p class="personal-info">EMAIL: {{ view.email }}</p>
      <p class="personal-info">WEBSITE: {{ view.webSite }}</p>
    </div>

    <!-- Experiences -->
    <div class="header-section">
      <p class="section-header">Experiences</p>
      <hr class="section-divider">
    </div>

    <div class="experience-section" v-for="(experience, index) in view.curriculumVitae.experiences" :key="index">
      <p class="experience-info">¤ {{ experience.title }} : {{ experience.nature }}.</p>
      <p class="experience-info">{{ experience.year }}.</p>
      <p class="experience-info">DESCRIPTION: {{ experience.description }}.</p>
      <p class="experience-info">WEBSITE: <a href="{{ experience.website }}">{{ experience.website }}</a></p>
      <hr class="experience-divider">
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



