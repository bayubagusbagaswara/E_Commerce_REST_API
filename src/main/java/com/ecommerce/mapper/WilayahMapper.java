package com.ecommerce.mapper;

import com.ecommerce.dto.region.kecamatan.KecamatanResponseDto;
import com.ecommerce.dto.region.kelurahan.KelurahanResponseDto;
import com.ecommerce.dto.region.district.KotaResponseDto;
import com.ecommerce.dto.region.province.ProvinsiResponseDto;
import com.ecommerce.entity.region.SubDistrict;
import com.ecommerce.entity.region.UrbanVillage;
import com.ecommerce.entity.region.District;
import com.ecommerce.entity.region.Province;
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

    public KecamatanResponseDto mapToKecamatanResponse(SubDistrict subDistrict) {
        KecamatanResponseDto kecamatanResponse = new KecamatanResponseDto();
        kecamatanResponse.setId(subDistrict.getId());
        kecamatanResponse.setCode(subDistrict.getCode());
        kecamatanResponse.setName(subDistrict.getName());
        kecamatanResponse.setCreatedDate(subDistrict.getCreatedDate());
        kecamatanResponse.setUpdatedDate(subDistrict.getUpdatedDate());
        kecamatanResponse.setKota(mapToKotaResponse(subDistrict.getDistrict()));
        return kecamatanResponse;
    }

    public KelurahanResponseDto mapToKelurahanResponse(UrbanVillage urbanVillage) {
        KelurahanResponseDto kelurahanResponse = new KelurahanResponseDto();
        kelurahanResponse.setId(urbanVillage.getId());
        kelurahanResponse.setCode(urbanVillage.getCode());
        kelurahanResponse.setName(urbanVillage.getName());
        kelurahanResponse.setCreatedDate(urbanVillage.getCreatedDate());
        kelurahanResponse.setUpdatedDate(urbanVillage.getUpdatedDate());
        kelurahanResponse.setKecamatan(mapToKecamatanResponse(urbanVillage.getSubDistrict()));
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

    public List<KecamatanResponseDto> mapToKecamatanResponseList(List<SubDistrict> subDistrictList) {
        return subDistrictList.stream()
                .map(this::mapToKecamatanResponse)
                .collect(Collectors.toList())
                ;
    }

    public List<KelurahanResponseDto> mapToKelurahanResponseList(List<UrbanVillage> urbanVillageList) {
        return urbanVillageList.stream()
                .map(this::mapToKelurahanResponse)
                .collect(Collectors.toList())
                ;
    }
}
