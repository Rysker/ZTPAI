# Model Base

## Description

Model Base is an web application that allows you to browse database of various model kits of planes, tanks and ships. Users can write reviews for selected models, add them to their wishlist, and to their collection.
Users can also customize their profile, and browse profile of other users.

Login Page
![image](https://github.com/Rysker/ZTPAI/assets/101675696/d189f4b7-3bc1-4200-a9cc-09730ee80aa0)

Vehicles Browser
![image](https://github.com/Rysker/ZTPAI/assets/101675696/35a6ebec-ce38-44c5-b7e9-fda2f4dd883c)

Model Kit Page
![image](https://github.com/Rysker/ZTPAI/assets/101675696/988b6f54-9c7e-44f7-b6a1-6c2f4dad08b1)

Collection Page
![image](https://github.com/Rysker/ZTPAI/assets/101675696/0ab8c25a-9bff-4f08-8f85-b864023467df)

Profile Page
![image](https://github.com/Rysker/ZTPAI/assets/101675696/27082ccf-e8ef-4144-b7a1-cda0b4e7d69c)


## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [License](#license)

## Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/Rysker/ZTPAI.git
2. You need to create local database from schema, and put data into it.
   
3. You need to configure a Google Cloud Storage. After configuring it be sure to perform 2 steps.

   a) In src/resources you need to add file:
   - modelbase_development.json
     
     You need to create service account and grant it an appropriate privileges. Once you have done this, you need to generate key for it, rename it and put it in src/resources
     
   b) Change values in application.properties, so backend can work with your storage.

5. If you set everything correctly, you can now start backend from IntelliJ
   ![image](https://github.com/Rysker/ZTPAI/assets/101675696/980504e6-047d-4133-9332-885b6534e93c)
   
6. To start frontend on Windows, you need to go to PowerShell, then navigate to frontend directory in project files and execute command
   ```cmd
   npm start

## Usage

1. Register and login: Start by creating an account and using it to access website.
2. Browse Models: Browse through various model kits of planes, tanks, and ships.
3. Model Kit Details: View detailed information about model kits and their reviews. You can write review, rate reviews of other users, report reviews, and add model to collection or wishlist.
4. User Profiles: Customize your profile by changing your description or avatar. Browse the profiles of others users.
5. Collection Management: View and manage the models in your collection. Update the status of your models and control which part of your collection would be visible to others.
6. Follow Users: You can add user's to your list of followed users. Their reviews would be distinguished from reviews of other users.

## License

This project is licensed under the [MIT License](LICENSE).
