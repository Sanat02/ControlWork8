<!DOCTYPE html>
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
        }

        .container {
            margin: 20px auto;
            max-width: 800px;
        }

        .header {
            font-size: 30px;
            margin-bottom: 20px;
        }

        .profile-photo {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 20px;
        }

        .photo-preview {
            width: 200px;
            height: 200px;
            border-radius: 50%;
            object-fit: cover;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }

        .upload-btn {
            background-color: greenyellow;
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            font-size: 18px;
            cursor: pointer;
        }

        .upload-btn:hover {
            background-color: lightgreen;
        }

        .upload-btn:focus {
            outline: none;
        }

        .submit-btn {
            background-color: greenyellow;
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            font-size: 18px;
            cursor: pointer;
        }

        .submit-btn:hover {
            background-color: lightgreen;
        }

        .submit-btn:focus {
            outline: none;
        }

        .footer {
            font-size: 14px;
            color: #6c757d;
        }

        #act {
            color: dodgerblue;
        }
    </style>
    <title>Load File</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css'>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/1.4.6/tailwind.min.css'>
</head>
<body>
<nav class="border-b px-4 py-2 bg-white">
    <div class="flex flex-wrap items-center justify-between md:justify-around">

        <div class="space-x-4">
            <a class="inline-block bg-blue-500 px-2 py-1 text-white font-semibold
                             text-sm rounded" href="/logout">Log out</a>
        </div>
    </div>
</nav>
<div class="container">
    <form action="/images/set" method="post" enctype="multipart/form-data">
        <label for="files">Select Images:</label>
        <input type="file" id="files" name="files" multiple required>
        <br><br>
        <label for="privacy">Select Privacy:</label>
        <select id="privacy" name="privacy" required>
            <option value="public">Public</option>
            <option value="private">Private</option>
        </select>
        <br><br>
        <input type="submit" value="Upload">
    </form>
</div>
<nav class="bg-white border-t fixed bottom-0 w-full">
    <ul class="flex justify-around py-2">
        <li class="flex-1 text-center">
            <a href="/" class="text-gray-600 hover:text-blue-500">
                <i class="fas fa-home text-xl"></i>
            </a>
        </li>
        <li class="flex-1 text-center">
            <a href="/#" id="act" class="text-gray-600 hover:text-blue-500">
                <i class="far fa-plus-square text-xl"></i>
            </a>
        </li>
        <li class="flex-1 text-center">
            <a href="/profile" class="text-gray-600 hover:text-blue-500">
                <i class="far fa-user text-xl"></i>
            </a>
        </li>
    </ul>

</nav>
</body>
</html>
