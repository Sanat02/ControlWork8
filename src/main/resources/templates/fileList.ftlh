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
            margin-top: 300px;
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 15px;
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
            width: 150px;
            display: inline-block;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            padding: 5px 10px;
            cursor: pointer;
            text-decoration: none;
        }

        .nbtn {
            width: 150px;
            display: inline-block;
            background-color: red;
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

        .pag {
            width: 150px;
            margin-left: 15px;
            margin-top: 10px;
            text-decoration: none;
            color: white;
            background-color:  #007bff;
            padding: 5px;
        }
    </style>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css'>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/1.4.6/tailwind.min.css'>
</head>
<body>
<nav class="border-b px-4 py-2 bg-white">
    <div class="flex flex-wrap items-center justify-between md:justify-around">

        <#if username??>
            <div class="space-x-4">
                <a class="inline-block bg-blue-500 px-2 py-1 text-white font-semibold
                             text-sm rounded" href="/logout">Log out</a>
            </div>
        <#else>
            <div class="space-x-4">
                <a class="inline-block bg-blue-500 px-2 py-1 text-white font-semibold
                             text-sm rounded" href="/login">Sign in</a>
            </div>

            <div class="space-x-4">
                <a class="inline-block bg-blue-500 px-2 py-1 text-white font-semibold
                             text-sm rounded" href="/register">Sign up</a>
            </div>
        </#if>
    </div>
</nav>

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
        <#list files.content as file>
            <tr>
                <td>${file.userId}</td>
                <td>${file.fileName}</td>
                <td>${file.status}</td>
                <#if username??>
                    <td>
                        <button type="button" id="deleteBtn-${file.fileName}" data-fileid="${file.fileName}"
                                class="delete-btn download-btn">Download
                        </button>
                    </td>
                <#else>
                    <#if file.status=="public">
                        <td>
                            <button type="button" id="deleteBtn-${file.fileName}" data-fileid="${file.fileName}"
                                    class="delete-btn download-btn">Download
                            </button>
                        </td>
                    <#else>
                        <td>
                            <a href="/login">
                                <button class="nbtn">
                                    Not available
                                </button>
                            </a>
                        </td>
                    </#if>
                </#if>
            </tr>
        </#list>
        </tbody>
    </table>
    <#if files.hasPrevious()>
        <a class="pag" href="/?page=${files.number - 1}">Previous Page</a>
    </#if>
    <#if files.hasNext()>
        <a class="pag" href="/?page=${files.number + 1}">Next Page</a>
    </#if>
</div>
<nav class="bg-white border-t fixed bottom-0 w-full">
    <ul class="flex justify-around py-2">
        <li class="flex-1 text-center">
            <a href="#" id="act" class="text-gray-600 hover:text-blue-500">
                <i class="fas fa-home text-xl"></i>
            </a>
        </li>
        <#if username??>
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
        </#if>
    </ul>

</nav>


<script>
    document.addEventListener("DOMContentLoaded", function () {
        const downloadButtons = document.querySelectorAll(".download-btn");

        downloadButtons.forEach(button => {
            button.addEventListener("click", function () {
                const fileName = button.getAttribute("data-fileid");
                const downloadUrl = `/posts/download/` + fileName;

                fetch(downloadUrl, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .then(response => response.blob())
                    .then(blob => {
                        const blobUrl = URL.createObjectURL(blob);
                        const link = document.createElement('a');
                        link.href = blobUrl;
                        link.download = fileName;
                        link.click();
                        URL.revokeObjectURL(blobUrl);
                    })
                    .catch(error => {
                        console.error('Error downloading file:', error);
                    });
            });
        });
    });
</script>

</body>
</html>
