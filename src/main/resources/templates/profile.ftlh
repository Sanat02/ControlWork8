<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Profile</title>

    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css'>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/1.4.6/tailwind.min.css'>
    <style>
        .pb-full {
            padding-bottom: 100%;
        }

        .bioclass {
            color: #8E8E8E;
        }


        .search-bar:focus + .fa-search {
            display: none;
        }

        #act {
            color: dodgerblue;
        }

        @media screen and (min-width: 768px) {
            .post:hover .overlay {
                display: block;
            }
        }

        .container {
            margin: 20px auto;
            max-width: 800px;
        }

        table {
            margin-top: 20px;
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

    </style>

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

<main class="bg-gray-100 bg-opacity-25">

    <div class="lg:w-8/12 lg:mx-auto mb-8">

        <header class="flex flex-wrap items-center p-4 md:py-8">

            <div class="md:w-3/12 md:ml-16">
                <!-- profile image -->
                <img class="w-20 h-20 md:w-40 md:h-40 object-cover rounded-full
                       border-2 border-pink-600 p-1" src="https://www.bytewebster.com/img/logo.png" alt="profile">
            </div>


            <div class="w-8/12 md:w-7/12 ml-4">
                <div class="md:flex md:flex-wrap md:items-center mb-4">
                    <h2 class="text-3xl inline-block font-light md:mr-2 mb-2 sm:mb-0">
                        ${account.accountName}
                    </h2>
                </div>


                <!-- user meta form medium screens -->
                <div class="hidden md:block">
                    <h1 class="font-semibold">${account.fullName}</h1>
                    <p>ByteWebster is a web development and coding blog website. Where we provide professional web
                        projects🌍</p>
                    <span><strong>${account.email}</strong></span>
                </div>

            </div>

            <!-- user meta form small screens -->
            <div class="md:hidden text-sm my-2">
                <h1 class="font-semibold">${account.fullName}</h1>
                <span class="bioclass">Internet company</span>
                <p>ByteWebster is a web development and coding blog website. Where we provide professional web
                    projects🌍</p>
                <span><strong>${account.email}</strong></span>
            </div>

        </header>

    </div>
</main>
<div class="container">
    <table>
        <thead>
        <tr>
            <th>File Name</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <#list account.images as image>
            <tr>
                <td>${image.fileName}</td>
                <td>${image.status}</td>
                <td>
                    <button type="button" id="deleteBtn-${image.getId()}" data-fileid="${image.getId()}"
                            class="delete-btn download-btn">Delete
                    </button>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>


<nav class="bg-white border-t fixed bottom-0 w-full">
    <ul class="flex justify-around py-2">
        <li class="flex-1 text-center">
            <a href="/" class="text-gray-600 hover:text-blue-500">
                <i class="fas fa-home text-xl"></i>
            </a>
        </li>
        <li class="flex-1 text-center">
            <a href="/images" class="text-gray-600 hover:text-blue-500">
                <i class="far fa-plus-square text-xl"></i>
            </a>
        </li>
        <li class="flex-1 text-center">
            <a href="#" id="act" class="text-gray-600 hover:text-blue-500">
                <i class="far fa-user text-xl"></i>
            </a>
        </li>
    </ul>

</nav>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const deleteButtons = document.querySelectorAll(".delete-btn");

        deleteButtons.forEach(button => {
            button.addEventListener("click", function () {
                const fileId = button.getAttribute("data-fileid");
                if (confirm('Are you sure you want to delete this file?')) {
                    fetch(`/delete/` + fileId, {
                        method: 'DELETE'
                    })
                        .then(response => {
                            if (response.ok) {
                                location.reload();
                            } else {
                                console.error('Error deleting file:', response.status);
                            }
                        })
                        .catch(error => {
                            console.error('Error deleting file:', error);
                        });
                }
            });
        });
    });
</script>
</body>
</html>
