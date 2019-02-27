Pod::Spec.new do |s|
  s.name        = "RNPure"
  s.version     = "1.0.13"
  s.homepage    = "http://fluxloop.com"
  s.summary     = "Pure wrapper for RN"
  s.license     = "MIT"
  s.author      = {"fluxLoop AS" => "social@fluxloop.com"}
  s.ios.deployment_target = '9.0'
  s.source      = { :git => "https://github.com/fluxloop/react-native-pure.git", :tag => "#{s.version}" }
  s.source_files    = "RNPure/**/*.{h,m}"
  s.preserve_paths  = "**/*.js"
  
  s.dependency "React"
  s.dependency "PureSDK"
end
