
## How to build the code

- Open the project in Android studio by clicking open and selecting root folder `TflRoadStatus`.
- Add the `apiKey` in config.json file under **app/src/main/res/raw** folder of the project
- Build it 

## How to run the output

- Connect a real device or an Android emulator running at leat Android 5
- Run the application via Android studio
- Enter a road number i.e. A2. If there are more than one roads returned based in input, the status of all the roads is displayed in a list

## How to run tests

- Tests are under folder **app/src/test** in Android Studio
- Select either any individual file or a folder
- Tests can be run either via right click and select run Test or via menu option/toolbar

## Assumptions

- Api error handling is limited to handle any 404 error with a message stating Road not found. For all other errors, it displays a generic message. This can be further enhanced to other Http error codes with retry options. 
- The contents of the error response are not parsed, only error code is used
- I have used a Mapper to map between server models to client models. In this example, it may not be used to its maxium but I think its good practice to keep server models separate from client models.

