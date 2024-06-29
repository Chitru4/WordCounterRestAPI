# WordCounterRestAPI

A REST API for counting words in text using Java and Spring Boot.

## Features

- Count total words in a given text
- Count occurrences of each word
- Simple and efficient API endpoints

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/Chitru4/WordCounterRestAPI.git
    ```
2. Navigate to the project directory:
    ```sh
    cd WordCounterRestAPI
    ```
3. Build the project using Maven:
    ```sh
    mvn clean install
    ```

### Running the Application

Start the application:
```sh
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`.

## API Endpoints

### Count Words

- **URL:** `/count`
- **Method:** `POST`
- **Content-Type:** `application/json`
- **Request Body:**
  ```json
  {
    "text": "your text here"
  }
  ```
- **Response:**
  ```json
  {
    "wordCount": {
      "total": 10,
      "words": {
        "your": 1,
        "text": 1,
        "here": 1
      }
    }
  }
  ```

## Testing

Run the tests using Maven:
```sh
mvn test
```

## Contributing

Contributions are welcome! Please submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
```

Feel free to customize this template based on your project's specific requirements and structure.

