package com.ecommerce.mapper;

import com.ecommerce.dto.region.subDistrict.SubDistrictDTO;
import com.ecommerce.dto.region.urbanVillage.KelurahanResponseDto;
import com.ecommerce.dto.region.district.DistrictDTO;
import com.ecommerce.dto.region.province.ProvinceDTO;
import com.ecommerce.entity.region.SubDistrict;
import com.ecommerce.entity.region.UrbanVillage;
import com.ecommerce.entity.region.District;
import com.ecommerce.entity.region.Province;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WilayahMapper {

    public ProvinceDTO mapToProvinsiResponse(Province province) {
        ProvinceDTO provinsiResponse = new ProvinceDTO();
        provinsiResponse.setId(province.getId());
        provinsiResponse.setCode(province.getCode());
        provinsiResponse.setName(province.getName());
        provinsiResponse.setCreatedDate(province.getCreatedDate());
        provinsiResponse.setUpdatedDate(province.getUpdatedDate());
        return provinsiResponse;
    }

    public DistrictDTO mapToKotaResponse(District district) {
        DistrictDTO kotaResponse = new DistrictDTO();
        kotaResponse.setId(district.getId());
        kotaResponse.setCode(district.getCode());
        kotaResponse.setName(district.getName());
        kotaResponse.setCreatedDate(district.getCreatedDate());
        kotaResponse.setUpdatedDate(district.getUpdatedDate());
        kotaResponse.setProvinsi(mapToProvinsiResponse(district.getProvince()));
        return kotaResponse;
    }

    public SubDistrictDTO mapToKecamatanResponse(SubDistrict subDistrict) {
        SubDistrictDTO kecamatanResponse = new SubDistrictDTO();
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

    public List<ProvinceDTO> mapToProvinsiResponseList(List<Province> provinceList) {
        return provinceList.stream()
                .map(this::mapToProvinsiResponse)
                .collect(Collectors.toList())
                ;
    }

    public List<DistrictDTO> mapToKotaResponseList(List<District> districtList) {
        return districtList.stream()
                .map(this::mapToKotaResponse)
                .collect(Collectors.toList())
                ;
    }

    public List<SubDistrictDTO> mapToKecamatanResponseList(List<SubDistrict> subDistrictList) {
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
