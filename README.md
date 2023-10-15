 # 1. Project Description

The data layer of this project is responsible for managing and persisting data related to CVs (Curriculum Vitae) and user information. This README provides till now an overview of the data layer, its structure, and the key components.

# Project Objectives

    Represent user data, including name, email, website, birthdate, and password.
    Manage CVs with activities described by year, nature, title, description, and website.
    Enable free access to user information and CVs.
    Require authentication for CV modifications.
    Support cooptation for creating new users.
    Handle a large volume of CVs (approximately 100,000).

# HOW TO RUN : 
```
git clone https://github.com/FaheDevs/GestionCv.git
cd GestionCv
git branch checkout feature/data-layer
mvn clean install
mvn test
```

Authors
@FaheDevs
