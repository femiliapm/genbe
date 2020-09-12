var tableBiodata = {
    create: function() {
        // jika table tersebut datatable, maka clear and dostroy
        if ($.fn.DataTable.isDataTable('#tableBiodata')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#tableBiodata').DataTable().clear();
            $('#tableBiodata').DataTable().destroy();
        }

        $('#tableBiodata').DataTable({
            data: input,
            columns: [{
                title: "Jenjang",
                data: "jenjang"
            }, {
                title: "Institusi",
                data: "institusi"
            }, {
                title: "Tahun Masuk",
                data: "masuk"
            }, {
                title: "Tahun Lulus",
                data: "lulus"
            }, {
                title: "Action",
                data: null,
                render: function(data, type, row, meta) {
                    return "<button class='btn-primary' onclick=formBiodata.setEditData('" + meta.row + "')>Edit</button>"
                }
            }]
        });
    }
};

var formBiodata = {
    resetForm: function() {
        $('#form-biodata')[0].reset();
    },

    saveFormPend: function() {
        var dataResult = getJsonForm($('#form-biodata').serializeArray(), true);
        input.push(dataResult);
        tableBiodata.create();
        $('#modal-biodata').modal('hide')
    },

    submitFormPend: function() {
        $.ajax({
            url: '/api/pendidikan/' + $('#idPerson').val(),
            method: 'post',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(input),
            success: function(res, status, xhr) {
                $('#idPerson').val('');
                input = [];
                if ($.fn.DataTable.isDataTable('#tableBiodata')) {
                    //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
                    $('#tableBiodata').DataTable().clear();
                    $('#tableBiodata').DataTable().destroy();
                };
                alert('{status: "true", message: "data berhasil masuk"}');
            },
            error: function(err) {
                console.log(err);
            }
        });
    },

    setEditData: function(index) {
        $('#form-biodata').fromJSON(JSON.stringify(input[index]));
        $('#modal-biodata').modal('show')
        newIn = index
    },

    saveFormPendEdit: function() {
        var dataResult = getJsonForm($('#form-biodata').serializeArray(), true);
        input[newIn] = dataResult;
        tableBiodata.create();
        $('#modal-biodata').modal('hide')
        newIn = -1;
    }
};