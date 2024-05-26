# Model Base

## Description

Model Base is an web application that allows you to browse database of various model kits of planes, tanks and ships. Users can write reviews for selected models, add them to their wishlist, and to their collection.
Users can also customize their profile, and browse profile of other users.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. First step clone this repository: git clone https://github.com/Rysker/ZTPAI.git
2. You need to create local databse from schema dump and data dump. (They will be provided once core features of application will be implemented)
3. Next you need to add additonal files that are not presented in the project.
   In src/resources you need to add 2 files:
   - keystore.p12
     Application will not run withouth this file, because SSL needs it.
   - application.properties 
     ![image](https://github.com/Rysker/ZTPAI/assets/101675696/7c28ccc6-0d10-4913-b389-6d397bd869a9)
     You need to adjust those options to how you set your database, and how you created your keystore.
4. If you set everything correctly, you can now start backend from IntelliJ
   ![image](https://github.com/Rysker/ZTPAI/assets/101675696/980504e6-047d-4133-9332-885b6534e93c)
5. To start frontend on Windows, you need to go to PowerShell, then navigate to frontend directory in project files and execute command "npm start"

## Usage

TODO

## Contributing

TODO

## License

This project is licensed under the [MIT License](LICENSE).
