package com.sillock.core.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SillockUserDtailsService implements UserDetailsService {
//    @Autowired
//    private MemberRepository memberRepository;
//
//    //들어온 email로 Member를 찾아 User객체로 반환
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         JwtUserDtails member = memberRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
//        return new member;
//    }
}