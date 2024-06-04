Trimmer APP.

Run the spring batch job which will process the input file with path: /resources/files/input.txt, which contains:

### 
     5 5
     1 2 N
     GAGAGAGAA
     3 3 E
     AADAADADDA
The batch will log the results and store them in json file with path: /resources/files/output.txt, the result will be in this format:

### 
     [
      {
        "x": 1,
        "y": 3,
        "direction": "N"
      },
      {
        "x": 5,
        "y": 1,
        "direction": "E"
      }
    ]
### OPTIONAL
You can configure the job using the application.yaml file to:
- process a local file
- configure a chunk size for the job main step (10 by default)


### Docker
You also build and run a docker image from the app jar:
- Build the project.
- Build the image

        docker build -t trimmerapp .
- Run the image passing the file path as it is in the Dockerfile

        docker run -it --rm --name trimmerapp -v my-volume:/app/output -e INPUT_FILE_PATH="/app/input.txt" trimmerapp
* If you want to see the output file contents, indicate a volume where it can be stored, then inspect the volume to find the output file.
