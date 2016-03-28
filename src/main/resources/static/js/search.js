var host = "http://localhost:8080/api/stackExchange/search?text=";

Date.prototype.format = function (mask, utc) {
    return dateFormat(this, mask, utc);
};

$(document).ready(function () {
    $('#search-string').on('keyup', function (e) {
        if (e.which === 13) {
            e.preventDefault();
            $('#search-button').trigger('click');
        }
    });

    $("#search-button").click(function () {
        $.ajax({
            url: "http://localhost:8080/api/stackExchange/search?text=" + document.getElementById('search-string').value || "",
            type: 'GET',
            datatype: 'application/json'
        }).then(function (resdata) {
            var html = '<thead><tr><th>Date</th><th>Owner</th><th>Title</th></tr></thead><tbody>';
            for (var k in resdata.data) {
                console.log(resdata.data[k]);
                var date = new Date(0); // The 0 there is the key, which sets the date to the epoch
                date.setUTCSeconds(resdata.data[k].date);
                var formatedDate = date.format("dd.mm.yyyy HH:MM:ss");
                html = html +
                    '<tr ' + ((resdata.data[k].isAnswered) ? 'class="success" ' : '') + '><td>' + formatedDate + '</td><td>' + resdata.data[k].ownerName + '</td><td><a href="' + resdata.data[k].url + '">' + resdata.data[k].title + '</a></td></tr>';
            }
            html = html + '</tbody>';
            document.getElementById('result-table').innerHTML = html;

        });
    });
});




