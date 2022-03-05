package com.restful.dto;

import com.restful.dto.kecamatan.KecamatanResponseDto;
import com.restful.dto.kelurahan.KelurahanResponseDto;
import com.restful.dto.kota.KotaResponseDto;
import com.restful.dto.provinsi.ProvinsiResponseDto;
import com.restful.entity.address.Kecamatan;
import com.restful.entity.address.Kelurahan;
import com.restful.entity.address.Kota;
import com.restful.entity.address.Provinsi;

import java.util.List;
import java.util.stream.Collectors;

public class WilayahMapper {

    // Mapping from Provinsi to ProvinsiResponseDto
    public static ProvinsiResponseDto mapProvinsiToProvinsiResponseDto(Provinsi provinsi) {
        ProvinsiResponseDto dto = new ProvinsiResponseDto();
        dto.setId(provinsi.getId());
        dto.setCode(provinsi.getCode());
        dto.setName(provinsi.getName());
        dto.setCreatedAt(provinsi.getCreatedAt());
        dto.setUpdatedAt(provinsi.getUpdatedAt());
        return dto;
    }

    // Mapping from Kota to KotaResponseDto
    public static KotaResponseDto mapKotaToKotaResponseDto(Kota kota) {
        KotaResponseDto dto = new KotaResponseDto();
        dto.setId(kota.getId());
        dto.setCode(kota.getCode());
        dto.setName(kota.getName());
        dto.setCreatedAt(kota.getCreatedAt());
        dto.setUpdatedAt(kota.getUpdatedAt());
        dto.setProvinsiResponseDto(mapProvinsiToProvinsiResponseDto(kota.getProvinsi()));
        return dto;
    }

    // Mapping from Kecamatan to KecamatanResponseDto
    public static KecamatanResponseDto mapKecamatanToKecamatanResponseDto(Kecamatan kecamatan) {
        KecamatanResponseDto dto = new KecamatanResponseDto();
        dto.setId(kecamatan.getId());
        dto.setCode(kecamatan.getCode());
        dto.setName(kecamatan.getName());
        dto.setCreatedAt(kecamatan.getCreatedAt());
        dto.setUpdatedAt(kecamatan.getUpdatedAt());
        dto.setKotaResponseDto(mapKotaToKotaResponseDto(kecamatan.getKota()));
        return dto;
    }

    // Mapping from Kelurahan to KelurahanResponseDto
    public static KelurahanResponseDto mapKelurahanToKelurahanResponseDto(Kelurahan kelurahan) {
        KelurahanResponseDto dto = new KelurahanResponseDto();
        dto.setPostalCode(kelurahan.getPostalCode());
        dto.setId(kelurahan.getId());
        dto.setCode(kelurahan.getCode());
        dto.setName(kelurahan.getName());
        dto.setCreatedAt(kelurahan.getCreatedAt());
        dto.setUpdatedAt(kelurahan.getUpdatedAt());
        dto.setKecamatanResponseDto(mapKecamatanToKecamatanResponseDto(kelurahan.getKecamatan()));
        return dto;
    }

    // Mapping from ProvinsiList to ProvinsiResponseList
    public static List<ProvinsiResponseDto> mapProvinsiListToProvinsiResponseDtoList(List<Provinsi> provinsiList) {
        return provinsiList.stream()
                .map(WilayahMapper::mapProvinsiToProvinsiResponseDto)
                .collect(Collectors.toList())
                ;
    }

    // Mapping from KotaList to KotaResponseDtoList
    public static List<KotaResponseDto> mapKotaListToKotaResponseDtoList(List<Kota> kotaList) {
        return kotaList.stream()
                .map(WilayahMapper::mapKotaToKotaResponseDto)
                .collect(Collectors.toList())
                ;
    }

    // Mapping from KecamatanList to KecamatanResponseDtoList
    public static List<KecamatanResponseDto> mapKecamatanListToKecamatanDtoList(List<Kecamatan> kecamatanList) {
        return kecamatanList.stream()
                .map(WilayahMapper::mapKecamatanToKecamatanResponseDto)
                .collect(Collectors.toList())
                ;
    }

    // Mapping from KelurahanList to KelurahanResponseDtoList
    public static List<KelurahanResponseDto> mapKelurahanListToKelurahanDtoList(List<Kelurahan> kelurahanList) {
        return kelurahanList.stream()
                .map(WilayahMapper::mapKelurahanToKelurahanResponseDto)
                .collect(Collectors.toList())
                ;
    }
}
