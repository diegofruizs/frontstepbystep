insert into BookEntity (id, name, isbn, image, description, publishDate, editorialid) 
values (1, 'The Lord of the Rings', '930330149-8', 'https://images-na.ssl-images-amazon.com/images/I/516GyHY9p6L.jpg', 'Supplement R Tympanic Membrane with Synth Sub, Via Opening', '8/20/1996','1');
insert into BookEntity (id, name, isbn, image, description, publishDate, editorialid) values (2, 'Solarbreeze', '507119915-7', 'http://dummyimage.com/150x116.png/ff4444/ffffff', 'Occlusion of Right Femoral Artery, Percutaneous Approach', '2/19/2014');
insert into BookEntity (id, name, isbn, image, description, publishDate, editorialid) values (3, 'Wrapsafe', '279453624-9', 'http://dummyimage.com/192x240.png/5fa2dd/ffffff', 'Removal of Spacer from T-lum Jt, Perc Approach', '4/7/1998');
insert into BookEntity (id, name, isbn, image, description, publishDate, editorialid) values (4, 'Quo Lux', '744706866-7', 'http://dummyimage.com/242x250.jpg/ff4444/ffffff', 'Reposition Left Femoral Shaft, Perc Endo Approach', '10/10/1998');
insert into BookEntity (id, name, isbn, image, description, publishDate, editorialid) values (5, 'Asoka', '260760424-9', 'http://dummyimage.com/158x100.png/dddddd/000000', 'Supplement Lower Artery with Autol Sub, Perc Approach', '5/9/2013');

[{
        "id": 1,
        "name": "The Lord of the Rings",
        "image": "https://images-na.ssl-images-amazon.com/images/I/516GyHY9p6L.jpg",
        "isbn": "930330149-8",
        "description": "Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.",
        "publishingdate": "2017/01/06",
        "editorial": {
            "id": 1,
            "name": "Oveja Negra"
        },
        "authors": [{
                "id": 2,
                "name": "J. R. R. Tolkien",
                "birthDate": "1892/01/03"
            }]
    }, {
        "id": 2,
        "name": "Harry Potter and the Sorcerer's Stone",
        "image": "http://m.cdn.blog.hu/ko/kockagyar/image/harry_potter_poster/harry_potter_1.jpg",
        "isbn": "077296395-9",
        "description": "Suspendisse accumsan tortor quis turpis.",
        "publishingdate": "2016/04/18",
        "editorial": {
            "id": 2,
            "name": "Siruela"
        },
        "authors": [{
                "id": 1,
                "name": "J.K. Rowling",
                "birthDate": "1965/07/31"
            }]
    },{
        "id": 3,
        "name": "A Game of Thrones",
        "image": "http://t1.gstatic.com/images?q=tbn:ANd9GcQEV8WgR73kXg3mpoFrUiOHaX9eUxe5K7Z4sN-u-ORABH8nwIm4",
        "isbn": "631383488-7",
        "description": "Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui.",
        "publishingdate": "2016/11/22",
        "editorial": {
            "id": 2,
            "name": "Siruela"
        },
        "authors": [{
                "id": 3,
                "name": "George R. R. Martin",
                "birthDate": "1948/09/20"

            }]
    }, {
        "id": 4,
        "name": "The Winds of Winter",
        "image": "http://www.darkmediaonline.com/wp-content/uploads/2013/01/WindsofWinter.jpg",
        "isbn": "084007710-6",
        "description": "Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.",
        "publishingdate": "2016/12/17",
        "editorial": {
            "id": 2,
            "name": "Siruela"
        },
        "authors": [{
                "id": 3,
                "name": "George R. R. Martin",
                "birthDate": "1948/09/20"

            }]
    } , {
        "id": 5,
        "name": "The Slow Regard of Silent Things",
        "image": "http://www.patrickrothfuss.com/images/page/cover-slow-regard_277.jpg",
        "isbn": "267917365-1",
        "description": "Nulla mollis molestie lorem. Quisque ut erat.",
        "publishingdate": "2016/04/27",
        "editorial": {
            "id": 1,
            "name": "Oveja Negra"
        },
        "authors": [{
                "id": 4,
                "name": "Patrick Rothfuss",
                "birthDate": "1973/06/06"
            }]
    }, {
        "id": 6,
        "name": "Harry Potter and the Philosopher's Stone",
        "image": "https://katemacdonalddotnet.files.wordpress.com/2015/11/potter-1-4.jpg",
        "isbn": "077296395-9",
        "description": "Suspendisse accumsan tortor quis turpis.",
        "publishingdate": "2016/04/18",
        "editorial": {
            "id": 2,
            "name": "Siruela"
        },
        "authors": [{
                "id": 1,
                "name": "J.K. Rowling",
                "birthDate": "1965/07/31"
            }]
    }]
