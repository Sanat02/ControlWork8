<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            margin: 20px auto;
            max-width: 800px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        .download-btn {
            display: inline-block;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            padding: 5px 10px;
            cursor: pointer;
            text-decoration: none;
        }

        .download-btn:hover {
            background-color: #0056b3;
        }

        #act {
            color: dodgerblue;
        }
    </style>
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
    <h1>${hello}</h1>
<div class="container">
    <table>
        <thead>
        <tr>
            <th>User ID</th>
            <th>File Name</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <#list files as file>
        <tr>
            <td>${file.userId}</td>
            <td>${file.fileName}</td>
            <td>${file.status}</td>
            <td><a href="/download/File1.jpg" class="download-btn">Download</a></td>
        </tr>
       </#list>

        </tbody>
    </table>
</div>
<nav class="bg-white border-t fixed bottom-0 w-full">
    <ul class="flex justify-around py-2">
        <li class="flex-1 text-center">
            <a href="#" id="act" class="text-gray-600 hover:text-blue-500">
                <i class="fas fa-home text-xl"></i>
            </a>
        </li>
        <li class="flex-1 text-center">
            <a href="/images" class="text-gray-600 hover:text-blue-500">
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
