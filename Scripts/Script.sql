select tp.nik, tp.nama, tp.alamat,
tb.nohp, tb.tanggal_lahir, tb.tempat_lahir,
tp2.jenjang as pendidikan_terakhir
from  t_person tp 
join t_biodata tb on tp.id_person = tb.idperson
join t_pendidikan tp2 on tp.id_person =tp2.idperson 