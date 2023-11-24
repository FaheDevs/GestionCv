export const Dashboard = Vue.component('dashboard', {
    props: ['person'],
    template: `
<div>
    <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
  <symbol id="door-closed" viewBox="0 0 16 16">
    <path d="M3 2a1 1 0 0 1 1-1h8a1 1 0 0 1 1 1v13h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V2zm1 13h8V2H4v13z"/>
    <path d="M9 9a1 1 0 1 0 2 0 1 1 0 0 0-2 0z"/>
  </symbol>
  <symbol id="file-earmark" viewBox="0 0 16 16">
    <path d="M14 4.5V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h5.5L14 4.5zm-3 0A1.5 1.5 0 0 1 9.5 3V1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h-2z"/>
  </symbol>
  <symbol id="file-earmark-text" viewBox="0 0 16 16">
    <path d="M5.5 7a.5.5 0 0 0 0 1h5a.5.5 0 0 0 0-1h-5zM5 9.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5z"/>
    <path d="M9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4.5L9.5 0zm0 1v2A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5z"/>
  </symbol>
  <symbol id="house-fill" viewBox="0 0 16 16">
    <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5Z"/>
    <path d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6Z"/>
  </symbol>
</svg>
  <header class="navbar sticky-top bg-dark flex-md-nowrap p-0 shadow" data-bs-theme="dark">
      <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6 text-white" href="#">{{person.firstName}}</a>
       
    
      
</header>

    <div class="container-fluid">
                  <div class="row">
                        <div class="sidebar border border-right col-md-3 col-lg-2 p-0 bg-body-tertiary">
                          <div class="offcanvas-md offcanvas-end bg-body-tertiary" tabindex="-1" id="sidebarMenu" aria-labelledby="sidebarMenuLabel">
                                        <div class="offcanvas-header">
                                          <h5 class="offcanvas-title" id="sidebarMenuLabel">Company name</h5>
                                          <button type="button" class="btn-close" data-bs-dismiss="offcanvas" data-bs-target="#sidebarMenu" aria-label="Close"></button>
                                        </div>
                                        <div class="offcanvas-body d-md-flex flex-column p-0 pt-lg-3 overflow-y-auto">
                                          <ul class="nav flex-column">
                                            <li class="nav-item">
                                              <a class="nav-link d-flex align-items-center gap-2 active" aria-current="page" href="#/">
                                                <svg class="bi"><use xlink:href="#house-fill"/></svg>
                                                Home
                                              </a>
                                            </li>
                                            <li class="nav-item">
                                              <a class="nav-link d-flex align-items-center gap-2" href="#">
                                                <svg class="bi"><use xlink:href="#file-earmark"/></svg>
                                                add Person
                                              </a>
                                            </li>
                                           
                                
                                          <hr class="my-3">
                                
                                          <ul class="nav flex-column mb-auto">
                                            <li class="nav-item">
                                              <a class="nav-link d-flex align-items-center gap-2" href="#">
                                                <svg class="bi"><use xlink:href="#door-closed"/></svg>
                                                Sign out
                                              </a>
                                            </li>
                                          </ul>
                                        </div>
                          </div>
                        </div>
                
                                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                                      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                                        <h1 class="h2">Dashboard</h1>
                                      </div>
                                      <h4>{{person.firstName + '' + person.lastName}}</h4>
                                      
                                    </main>
                  </div>
    </div>
</div>
</div>
              
      
  `,
    data() {
        return {
        };
    },
    methods: {


    }
});



