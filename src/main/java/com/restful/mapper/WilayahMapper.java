package com.restful.mapper;

import com.restful.dto.region.kecamatan.KecamatanResponseDto;
import com.restful.dto.region.kelurahan.KelurahanResponseDto;
import com.restful.dto.region.kota.KotaResponseDto;
import com.restful.dto.region.provinsi.ProvinsiResponseDto;
import com.restful.entity.region.Kecamatan;
import com.restful.entity.region.Kelurahan;
import com.restful.entity.region.District;
import com.restful.entity.region.Province;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WilayahMapper {

    public ProvinsiResponseDto mapToProvinsiResponse(Province province) {
        ProvinsiResponseDto provinsiResponse = new ProvinsiResponseDto();
        provinsiResponse.setId(province.getId());
        provinsiResponse.setCode(province.getCode());
        provinsiResponse.setName(province.getName());
        provinsiResponse.setCreatedDate(province.getCreatedDate());
        provinsiResponse.setUpdatedDate(province.getUpdatedDate());
        return provinsiResponse;
    }

    public KotaResponseDto mapToKotaResponse(District district) {
        KotaResponseDto kotaResponse = new KotaResponseDto();
        kotaResponse.setId(district.getId());
        kotaResponse.setCode(district.getCode());
        kotaResponse.setName(district.getName());
        kotaResponse.setCreatedDate(district.getCreatedDate());
        kotaResponse.setUpdatedDate(district.getUpdatedDate());
        kotaResponse.setProvinsi(mapToProvinsiResponse(district.getProvince()));
        return kotaResponse;
    }

    public KecamatanResponseDto mapToKecamatanResponse(Kecamatan kecamatan) {
        KecamatanResponseDto kecamatanResponse = new KecamatanResponseDto();
        kecamatanResponse.setId(kecamatan.getId());
        kecamatanResponse.setCode(kecamatan.getCode());
        kecamatanResponse.setName(kecamatan.getName());
        kecamatanResponse.setCreatedDate(kecamatan.getCreatedDate());
        kecamatanResponse.setUpdatedDate(kecamatan.getUpdatedDate());
        kecamatanResponse.setKota(mapToKotaResponse(kecamatan.getDistrict()));
        return kecamatanResponse;
    }

    public KelurahanResponseDto mapToKelurahanResponse(Kelurahan kelurahan) {
        KelurahanResponseDto kelurahanResponse = new KelurahanResponseDto();
        kelurahanResponse.setId(kelurahan.getId());
        kelurahanResponse.setCode(kelurahan.getCode());
        kelurahanResponse.setName(kelurahan.getName());
        kelurahanResponse.setCreatedDate(kelurahan.getCreatedDate());
        kelurahanResponse.setUpdatedDate(kelurahan.getUpdatedDate());
        kelurahanResponse.setKecamatan(mapToKecamatanResponse(kelurahan.getKecamatan()));
        return kelurahanResponse;
    }

    public List<ProvinsiResponseDto> mapToProvinsiResponseList(List<Province> provinceList) {
        return provinceList.stream()
                .map(this::mapToProvinsiResponse)
                .collect(Collectors.toList())
                ;
    }

    public List<KotaResponseDto> mapToKotaResponseList(List<District> districtList) {
        return districtList.stream()
                .map(this::mapToKotaResponse)
                .collect(Collectors.toList())
                ;
    }

    public List<KecamatanResponseDto> mapToKecamatanResponseList(List<Kecamatan> kecamatanList) {
        return kecamatanList.stream()
                .map(this::mapToKecamatanResponse)
                .collect(Collectors.toList())
                ;
    }

    public List<KelurahanResponseDto> mapToKelurahanResponseList(List<Kelurahan> kelurahanList) {
        return kelurahanList.stream()
                .map(this::mapToKelurahanResponse)
                .collect(Collectors.toList())
                ;
    }
}
