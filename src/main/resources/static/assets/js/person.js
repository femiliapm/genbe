var tableBiodata = {
    create: function () {
        // jika table tersebut datatable, maka clear and destroy
        if ($.fn.DataTable.isDataTable('#tableBiodata')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#tableBiodata').DataTable().clear();
            $('#tableBiodata').DataTable().destroy();
        }

        $.ajax({
            url: '/person',
            type: 'get',
            contentType: 'application/json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#tableBiodata').DataTable({
                        data: res,
                        columns: [{
                                title: "NIK",
                                data: "nik"
                            }, {
                                title: "Nama Lengkap",
                                data: "name"
                            }, {
                                title: "Alamat",
                                data: "address"
                            }, {
                                title: "No. Hp",
                                data: "hp"
                            }, {
                                title: "Tanggal Lahir",
                                data: "tgl"
                            }, {
                                title: "Tempat Lahir",
                                data: "tempatLahir"
                            }, {
                                title: "Action",
                                data: null,
                                render: function (data, type, row) {
                                    console.log(data)
                                    return "<button class='btn-primary' onclick=formBiodata.setEditData('" + data.idBio + "')>Edit</button>"
                                }
                            }
                        ]
                    });

                } else {}
            },
            error: function (err) {
                console.log(err);
            }
        });

    }
};

var formBiodata = {
    resetForm: function () {
        $('#form-biodata')[0].reset();
    },
    saveForm: function () {
        if ($('#form-biodata').parsley().validate()) {
            var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);

            $.ajax({
                url: '/person',
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(dataResult),
                success: function (res, status, xhr) {
                    var response = res.status;
                    var message = res.message;
                    console.log(response);
                    console.log(message);
                    if (response == 'true') {
                        tableBiodata.create();
                        $('#modal-biodata').modal('hide');
                        alert('{status: "true", message: "data berhasil masuk"}');
                    } else if (response == 'false' && message.includes('nik')) {
                        alert('{status: "false", message: "data gagal masuk, jumlah digit nik tidak sama dengan 16"}');
                    } else if (response == 'false' && message.includes('umur')) {
                        alert('{status: "false", message: "data gagal masuk, umur kurang dari 30 tahun"}');
                    } else {}
                },
                error: function (err) {
                    console.log(err);
                    console.log(JSON.stringify(dataResult))
                }
            });
        }
    },
    setEditData: function (idCabang) {
        formBiodata.resetForm();

        $.ajax({
            url: '/person/-id/' + idCabang,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#form-biodata').fromJSON(JSON.stringify(res));
                    $('#modal-biodata').modal('show')

                } else {}
            },
            error: function (err) {
                console.log(err);
            }
        });
    },
    getDataByNik: function (nik) {
        if ($.fn.DataTable.isDataTable('#tableBiodata')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#tableBiodata').DataTable().clear();
            $('#tableBiodata').DataTable().destroy();
        }

        $.ajax({
            url: '/person/' + nik,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (result) {
                if (result[0].status == 'true' && result[0].message == 'success') {
                    $('#tableBiodata').DataTable({
                        data: [result[0].data],
                        columns: [{
                                title: "NIK",
                                data: "nik"
                            }, {
                                title: "Nama Lengkap",
                                data: "name"
                            }, {
                                title: "Alamat",
                                data: "address"
                            }, {
                                title: "No. Hp",
                                data: "hp"
                            }, {
                                title: "Tanggal Lahir",
                                data: "tgl"
                            }, {
                                title: "Tempat Lahir",
                                data: "tempatLahir"
                            }, {
                                title: "Umur",
                                data: "umur"
                            }, {
                                title: "Pendidikan Terakhir",
                                data: "pendidikan_terakhir"
                            }, {
                                title: "Action",
                                data: null,
                                render: function (data, type, row) {
                                    return "<button class='btn-primary' onclick=formBiodata.setEditData('" + data.id + "')>Edit</button>"
                                }
                            }
                        ]
                    });
                    alert('{status: "true", message: "success"}');
                } else if (result[0].status == 'true' && result[0].message.includes('ditemukan')) {
                    alert('{status: "true", message: "data dengan nik ' + nik + ' tidak tersedia"}');
                } else if (result[0].status == 'false' && result[0].message.includes('gagal')) {
                    alert('{status: "true", message: "data dengan nik ' + nik + ' tidak tersedia"}');					
				}
                console.log(result);
            },
            error: function (err) {
                console.log(err);
            }
        });
    }
};
