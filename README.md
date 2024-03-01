# enviro365-Investments
## Introduction

This documentation provides information on setting up and using the application along with MailHog for email testing.

## Prerequisites

- Docker installed on your machine.

## Getting Started

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/B-Tshehla/enviro365-Investments.git
    cd enviro365-Investments
    ```

2. **Run MailHog:**

   MailHog is included in the Docker Compose file in the root directory. Navigate to the root directory and run:

    ```bash
    docker-compose up -d
    ```

   MailHog will be accessible at [http://localhost:8025](http://localhost:8025) for viewing sent emails and at port 1025 for SMTP.

3. **Run the Application:**

   Start your application. The application runs on http://localhost:8081 by default.

4. **Access Swagger UI:**

   Open your web browser and navigate to [Swagger UI](http://localhost:8081/swagger-ui/index.html#/). This is the interactive documentation for your application's API.

## MailHog Setup

- **MailHog Web Interface:**

  The MailHog web interface is accessible at [http://localhost:8025](http://localhost:8025). You can use this interface to view emails sent by your application.

- **SMTP Server:**

  MailHog's SMTP server is running at `localhost:1025`. Configure your application to use this SMTP server for sending emails in your local environment.