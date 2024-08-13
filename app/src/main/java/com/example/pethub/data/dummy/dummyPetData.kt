package com.example.pethub.data.dummy

import com.example.pethub.R
import com.example.pethub.data.model.Owner
import com.example.pethub.data.model.Pet

fun getDummyPetData(): List<Pet> {
    val dummyOwner = listOf(
        Owner(
            id = "q32rfdawda",
            name = "Chaerul Candra Pranugrah",
            role = "Pet Lover",
            number = "+62812312312312",
            image = R.drawable.pict1,
        ),
        Owner(
            id = "231edwafa",
            name = "Michael Dam",
            role = "Pet Lover",
            number = "+62812312312312",
            image = R.drawable.pict2,
        ),
        Owner(
            id = "643qbwWDWEQ",
            name = "Erik Lucatero",
            role = "Pet Lover",
            number = "+62812312312312",
            image = R.drawable.pict3,
        )
    )

    return listOf(
        Pet(
            id = "q32rfdawda",
            name = "Kitty",
            age = "1",
            gender = "Male",
            location = 23,
            image = "https://placekitten.com/1920/1080?image=1",
            postTime = 42,
            description = "Meet kitty, the elegant and graceful cat with a sleek black coat. He enjoys lounging in sunny spots and will purr her way into your heart. Luna is seeking a loving family who appreciates her gentle nature",
            color = "Brown",
            weight = "1,2Kg",
            owner = dummyOwner[0]
        ),
        Pet(
            id = "1T23RESAF",
            name = "Mika",
            age = "3",
            gender = "Female",
            location = 45,
            image = "https://placekitten.com/1920/1080?image=3",
            postTime = 31,
            description = "Mika is a curious and adventurous cat who loves climbing to new heights. With his striking orange fur, Milo is not only handsome but also a great companion for cozy evenings on the couch.",
            color = "Black",
            weight = "4,5Kg",
            owner = dummyOwner[1]
        ),
        Pet(
            id = "qb344qbf",
            name = "Cleo",
            age = "2",
            gender = "Female",
            location = 125,
            image = "https://placekitten.com/1920/1080?image=4",
            postTime = 51,
            description = "Cleo is a regal and sophisticated cat with a beautiful calico coat. She enjoys lounging in high places and observing her surroundings. Cleo is looking for a calm and loving home where she can be treated like the queen she is.",
            color = "Orange",
            weight = "5,3Kg",
            owner = dummyOwner[2]
        ),
        Pet(
            id = "21qe2qw",
            name = "Mitten",
            age = "1",
            gender = "Female",
            location = 125,
            image = "https://placekitten.com/1920/1080?image=5",
            postTime = 78,
            description = "Mitten is a mysterious and independent cat with a striking gray coat. She enjoys exploring her surroundings and would thrive in a home with plenty of nooks and crannies to investigate. Misty is looking for a patient and understanding friend to share her world with.",
            color = "Black",
            weight = "0,4Kg",
            owner = dummyOwner[2]
        ),
        Pet(
            id = "averwaer",
            name = "Gizmo",
            age = "1",
            gender = "Male",
            location = 175,
            image = "https://placekitten.com/1920/1080?image=6",
            postTime = 151,
            description = "Gizmo is a quirky and charming cat with a penchant for entertaining antics. This fluffy ball of energy is always ready to chase toys and make you smile. Adopt Gizmo for a daily dose of joy and laughter.",
            color = "Orange",
            weight = "1,2Kg",
            owner = dummyOwner[0]
        ),
        Pet(
            id = "432fc4q3",
            name = "Whiskers",
            age = "4",
            gender = "Male",
            location = 203,
            image = "https://placekitten.com/1920/1080?image=9",
            postTime = 231,
            description = "Whiskers is a playful and affectionate cat with a charming personality. This mischievous feline loves to explore and is looking for a forever home with a cozy spot by the window for bird watching.",
            color = "Black",
            weight = "6,2Kg",
            owner = dummyOwner[0]
        ),
        Pet(
            id = "rwgggw4tg",
            name = "Bella",
            age = "3",
            gender = "Female",
            location = 263,
            image = "https://placekitten.com/1920/1080?image=10",
            postTime = 431,
            description = "Bella is a sweet and shy cat with a luxurious long-haired coat. She enjoys quiet moments and would thrive in a calm and loving environment. Bella is looking for someone patient to help her build trust and share a lifetime of cuddles.",
            color = "Black",
            weight = "4,8Kg",
            owner = dummyOwner[1]
        ),
        Pet(
            id = "246b5bas",
            name = "Simba",
            age = "5",
            gender = "Male",
            location = 313,
            image = "https://placekitten.com/1920/1080?image=12",
            postTime = 831,
            description = "Simba is a bold and confident cat who loves to be the center of attention. With his striking golden fur and expressive eyes, Simba is sure to capture your heart. He's ready for a home where he can rule as the majestic king of the castle.",
            color = "Orange",
            weight = "7,1Kg",
            owner = dummyOwner[1]
        ),
        Pet(
            id = "w564bhw45by",
            name = "Tiger",
            age = "2",
            gender = "Female",
            location = 513,
            image = "https://placekitten.com/1920/1080?image=14",
            postTime = 1231,
            description = "Tiger is a confident and adventurous tabby cat with striking markings. This playful kitty enjoys exploring the great outdoors and is seeking an adopter who shares his love for excitement and discovery.",
            color = "Orange",
            weight = "2,7Kg",
            owner = dummyOwner[2]
        ),
        Pet(
            id = "bwq434556w",
            name = "Max",
            age = "4",
            gender = "Male",
            location = 713,
            image = "https://placekitten.com/1920/1080?image=16",
            postTime = 2831,
            description = "Max is a laid-back and easygoing cat with a fluffy tail and a heartwarming personality. This chill kitty is ready to join your family for relaxed evenings and sunny afternoons of lounging.",
            color = "Gray",
            weight = "6,9Kg",
            owner = dummyOwner[0]
        ),
    )
}